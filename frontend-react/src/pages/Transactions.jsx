import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getTransactions, cancelTransaction } from "../api/transactionApi";

import {
  Box,
  Paper,
  Typography,
  Button,
  Stack,
  Snackbar,
  Alert,
} from "@mui/material";

import { DataGrid } from "@mui/x-data-grid";

export default function Transactions() {
  const navigate = useNavigate();

  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);

  const [open, setOpen] = useState(false);
  const [message, setMessage] = useState("");

  const fetchData = async () => {
    setLoading(true);
    try {
      const res = await getTransactions(0, 20, "id");

      setRows(
        res.data.content.map((item) => ({
          ...item,
          id: item.id,
        }))
      );
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleCancel = async (row) => {
    try {
      await cancelTransaction({
        id: row.id,
        referencia: row.referencia,
        estatus: "cancelado",
      });

      setMessage("Transacción cancelada correctamente");
      setOpen(true);

      fetchData();
    } catch (err) {
      console.error(err);
      setMessage("Error cancelando transacción");
      setOpen(true);
    }
  };

  const columns = [
    { field: "id", headerName: "ID", width: 80 },
    { field: "operacion", headerName: "Operación", flex: 1 },
    { field: "importe", headerName: "Importe", flex: 1 },
    { field: "cliente", headerName: "Cliente", flex: 1 },
    {
      field: "estatus",
      headerName: "Status",
      flex: 1,
      renderCell: (params) => {
        const value = params.value?.toLowerCase();

        return (
          <span
            style={{
              padding: "4px 10px",
              borderRadius: 10,
              background: value === "cancelado" ? "#ff5252" : "#4caf50",
              color: "white",
              fontWeight: "bold",
              fontSize: 12,
            }}
          >
            {value === "cancelado" ? "Cancelado" : "Aprobada"}
          </span>
        );
      },
    },
    {
      field: "actions",
      headerName: "Acciones",
      width: 160,
      sortable: false,
      filterable: false,
      renderCell: (params) => {
        const estatus = params.row.estatus?.toLowerCase();

        if (estatus === "cancelado") {
          return (
            <span style={{ fontSize: 12, color: "#999" }}>
              Sin acciones
            </span>
          );
        }

        return (
          <Button
            size="small"
            variant="contained"
            color="error"
            onClick={() => handleCancel(params.row)}
            sx={{
              textTransform: "none",
              borderRadius: 2,
              fontWeight: "bold",
            }}
          >
            Cancelar
          </Button>
        );
      },
    },
  ];

  return (
    <Box
      sx={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
        padding: 3,
      }}
    >
      <Paper
        elevation={12}
        sx={{
          borderRadius: 4,
          padding: 3,
        }}
      >
        {/* HEADER */}
        <Stack
          direction="row"
          justifyContent="space-between"
          alignItems="center"
          mb={2}
        >
          <Box>
            <Typography variant="h5" fontWeight="bold" color="primary">
              Transacciones
            </Typography>

            <Typography variant="body2" color="text.secondary">
              Gestión de operaciones del sistema
            </Typography>
          </Box>

          <Button
            variant="outlined"
            onClick={() => navigate("/dashboard")}
          >
            ⬅ Volver al dashboard
          </Button>
        </Stack>

        {/* TABLE (FIX REAL) */}
        <div style={{ height: 550, width: "100%" }}>
          <DataGrid
            rows={rows}
            columns={columns}
            loading={loading}
            disableRowSelectionOnClick
            autoHeight={false}
            density="standard"
            columnHeaderHeight={45}
            sx={{
              border: "none",

              // 🔥 ESTO ES LO QUE TE ESTABA ROMPIENDO LOS HEADERS
              "& .MuiDataGrid-columnHeaders": {
                backgroundColor: "#1e3c72",
                color: "#fff",
                fontWeight: "bold",
              },

              "& .MuiDataGrid-columnHeaderTitle": {
                color: "#fff",
                fontWeight: "bold",
              },

              "& .MuiDataGrid-row:hover": {
                backgroundColor: "rgba(30, 60, 114, 0.12)",
              },
            }}
          />
        </div>
      </Paper>

      {/* SNACKBAR */}
      <Snackbar
        open={open}
        autoHideDuration={3000}
        onClose={() => setOpen(false)}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
      >
        <Alert
          severity={message.includes("Error") ? "error" : "success"}
          variant="filled"
        >
          {message}
        </Alert>
      </Snackbar>
    </Box>
  );
}