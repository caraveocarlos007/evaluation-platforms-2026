import api from "../services/axiosConfig";

export const loginRequest = async (data) => {
  console.log("API HIT", data);

  const res = await api.post("/auth/login", data);

  console.log("API RESPONSE", res.data);

  return res;
};