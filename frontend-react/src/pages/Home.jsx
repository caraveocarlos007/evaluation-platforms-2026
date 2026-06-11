import { Box, Typography, Paper } from "@mui/material";

export default function Home() {
  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "#f4f6f8",
      }}
    >
      <Paper sx={{ padding: 4, borderRadius: 3 }}>
        <Typography variant="h4">Login OK ✔</Typography>
        <Typography color="text.secondary">
          Bienvenido al sistema
        </Typography>
      </Paper>
    </Box>
  );
}