import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginRequest } from "../api/authApi";
import { saveToken } from "../services/tokenService";

import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  CircularProgress,
} from "@mui/material";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const res = await loginRequest({ username, password });

      const token = res.data.token;

      if (!token) {
        setError("Credenciales inválidas. Favor de validar el usuario y la contraseña.");
        return;
      }

      saveToken(token);
      navigate("/dashboard");
    } catch (err) {
      setError("Credenciales incorrectas");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      }}
    >
      <Paper
        elevation={12}
        sx={{
          padding: 4,
          width: 360,
          borderRadius: 4,
          textAlign: "center",
          backdropFilter: "blur(10px)",
        }}
      >
        <Typography variant="h5" fontWeight="bold" mb={2} color="primary">
          Welcome Back
        </Typography>

        <Typography variant="body2" mb={2} color="text.secondary">
          Sign in to continue
        </Typography>

        <form onSubmit={handleLogin}>
          <TextField
            fullWidth
            label="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            margin="normal"
          />

          <TextField
            fullWidth
            label="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            margin="normal"
          />

          {error && (
            <Typography color="error" fontSize={14} sx={{ mt: 1 }}>
              {error}
            </Typography>
          )}

          <Button
            fullWidth
            type="submit"
            variant="contained"
            sx={{
              mt: 3,
              py: 1.3,
              borderRadius: 2,
              background: "linear-gradient(135deg, #1e3c72, #2a5298)",
              fontWeight: "bold",
              textTransform: "none",
            }}
            disabled={loading}
          >
            {loading ? <CircularProgress size={22} color="inherit" /> : "Login"}
          </Button>
        </form>
      </Paper>
    </Box>
  );
}