import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/Home";

const getPublicContent = () => {
  return axios.get(API_URL, { headers: authHeader() });
};


const UserService = {
  getPublicContent
};

export default UserService;