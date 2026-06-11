import api from "../services/axiosConfig";

// CREATE
export const createTransaction = (data) => {
  return api.post("/transactions", data);
};

// LIST (PAGINADO)
export const getTransactions = (page, size, sortBy) => {
  return api.get(`/transactions?page=${page}&size=${size}&sortBy=${sortBy}`);
};

// PATCH CANCELAR
export const cancelTransaction = (data) => {
  return api.patch("/transactions/status", data);
};