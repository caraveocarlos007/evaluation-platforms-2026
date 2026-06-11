  import axios from "axios";

  export const loginRequest = async (data) => {
    console.log("API HIT", data);

    const res = await axios.post(
      "http://localhost:8082/api/auth/login",
      data
    );

    console.log("API RESPONSE", res.data);

    return res;
  };