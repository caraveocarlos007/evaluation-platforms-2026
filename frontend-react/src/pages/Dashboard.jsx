import { useNavigate } from "react-router-dom";
import { Box, Paper, Typography, Button, Stack, Grid } from "@mui/material";

export default function Dashboard() {
  const navigate = useNavigate();

  const cards = [
    {
      title: "Crear transacción",
      desc: "Registrar una nueva operación",
      action: () => navigate("/create"),
      color: "linear-gradient(135deg, #1e3c72, #2a5298)",
    },
    {
      title: "Ver transacciones",
      desc: "Consultar historial completo",
      action: () => navigate("/transactions"),
      color: "linear-gradient(135deg, #0f2027, #2c5364)",
    },
    {
      title: "Cancelar transacción",
      desc: "Actualizar estatus a CANCELADA",
      action: () => navigate("/transactions"),
      color: "linear-gradient(135deg, #b91c1c, #ef4444)",
    },
    {
      title: "Cerrar sesión",
      desc: "Salir del sistema",
      action: () => {
        localStorage.clear();
        navigate("/");
      },
      color: "linear-gradient(135deg, #111827, #374151)",
    },
  ];

  return (
    <Box
      sx={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
        padding: 3,
      }}
    >
      <Paper
        elevation={12}
        sx={{
          width: 700,
          borderRadius: 4,
          padding: 4,
          textAlign: "center",
          backdropFilter: "blur(10px)",
        }}
      >
        <Typography variant="h4" fontWeight="bold" color="primary">
          Dashboard
        </Typography>

        <Typography variant="body2" sx={{ mt: 1, mb: 4 }} color="text.secondary">
          Sistema de Transacciones
        </Typography>

        <Grid container spacing={2}>
          {cards.map((card, index) => (
            <Grid item xs={12} sm={6} key={index}>
              <Paper
                onClick={card.action}
                sx={{
                  padding: 3,
                  borderRadius: 3,
                  cursor: "pointer",
                  color: "white",
                  background: card.color,
                  transition: "0.3s",
                  "&:hover": {
                    transform: "scale(1.03)",
                    opacity: 0.9,
                  },
                }}
              >
                <Typography variant="h6" fontWeight="bold">
                  {card.title}
                </Typography>
                <Typography variant="body2" sx={{ mt: 1 }}>
                  {card.desc}
                </Typography>
              </Paper>
            </Grid>
          ))}
        </Grid>

        <Stack direction="row" justifyContent="center" mt={4}>
          <Typography variant="caption" color="text.secondary">
            API Transactions System • 2026
          </Typography>
        </Stack>
      </Paper>
    </Box>
  );
}