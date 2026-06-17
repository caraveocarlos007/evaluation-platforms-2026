import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createTransaction } from "../api/transactionApi";
import CryptoJS from "crypto-js";

import {
  Box,
  Paper,
  TextField,
  Typography,
  Button,
  Stack,
  CircularProgress,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Alert,
} from "@mui/material";

const AES_KEY = "key123";

export default function CreateTransaction() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    operacion: "",
    importe: "",
    cliente: "",
    secreto: "",
  });

  const [loading, setLoading] = useState(false);
  const [open, setOpen] = useState(false);
  const [result, setResult] = useState(null);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async () => {
    setLoading(true);

    try {
      const key = CryptoJS.enc.Utf8.parse("1234567890123456");

      const encrypted = CryptoJS.AES.encrypt(
        form.secreto,
        key,
        {
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7,
        }
      );

      const encryptedSecret = encrypted.toString();

      console.log("====================================");
      console.log("SECRETO ORIGINAL:", form.secreto);
      console.log("SECRETO CIFRADO:", encryptedSecret);
      console.log("====================================");

      const payload = {
        ...form,
        secreto: encryptedSecret,
      };

      const res = await createTransaction(payload);

      setResult(res.data);

      setOpen(true);

      setForm({
        operacion: "",
        importe: "",
        cliente: "",
        secreto: "",
      });

    } catch (err) {
      console.error(err);
      alert("Error creando transacción");
    } finally {
      setLoading(false);
    }
  };

  const handleNew = () => {
    setOpen(false);
    setResult(null);
  };

  const handleGoDashboard = () => {
    setOpen(false);
    navigate("/dashboard");
  };

  return (
    <>
      <Box
        sx={{
          height: "100vh",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          background:
            "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
          padding: 2,
        }}
      >
        <Paper
          elevation={12}
          sx={{
            width: 420,
            borderRadius: 4,
            padding: 4,
            textAlign: "center",
          }}
        >
          <Typography
            variant="h5"
            fontWeight="bold"
            color="primary"
          >
            Crear Transacción
          </Typography>

          <Typography
            variant="body2"
            sx={{ mb: 3 }}
            color="text.secondary"
          >
            Registra una nueva operación
          </Typography>

          <Stack spacing={2}>
            <TextField
              label="Operación"
              name="operacion"
              value={form.operacion}
              onChange={handleChange}
              fullWidth
            />

            <TextField
              label="Importe"
              name="importe"
              value={form.importe}
              onChange={handleChange}
              fullWidth
            />

            <TextField
              label="Cliente"
              name="cliente"
              value={form.cliente}
              onChange={handleChange}
              fullWidth
            />

            <TextField
              label="Secreto"
              name="secreto"
              value={form.secreto}
              onChange={handleChange}
              fullWidth
              type="password"
            />

            <Button
              variant="contained"
              onClick={handleSubmit}
              disabled={loading}
            >
              {loading ? (
                <CircularProgress
                  size={22}
                  color="inherit"
                />
              ) : (
                "Crear transacción"
              )}
            </Button>
          </Stack>
        </Paper>
      </Box>

      <Dialog
        open={open}
        onClose={handleNew}
        fullWidth
        maxWidth="xs"
      >
        <DialogTitle>
          Transacción creada
        </DialogTitle>

        <DialogContent>
          {result && (
            <Stack spacing={1} mt={1}>
              <Alert
                severity="success"
                variant="filled"
              >
                Operación registrada correctamente
              </Alert>

              <Typography>
                <b>ID:</b> {result.id}
              </Typography>

              <Typography>
                <b>Referencia:</b> {result.referencia}
              </Typography>

              <Typography>
                <b>Estatus:</b> {result.estatus}
              </Typography>
            </Stack>
          )}
        </DialogContent>

        <DialogActions>
          <Button
            onClick={handleNew}
            variant="outlined"
          >
            Crear otra
          </Button>

          <Button
            onClick={handleGoDashboard}
            variant="contained"
          >
            Ir al dashboard
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}