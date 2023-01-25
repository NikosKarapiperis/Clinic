import axios from 'axios'
import authHeader from "./auth-header";

const SECRETARY_REST_API_URL = 'http://localhost:8080/secretary';

class SecretaryService{

    getSecretary(){
        return axios.get(SECRETARY_REST_API_URL + '/getAll',{ headers: authHeader() });
    }

    save(secretary){
        return axios.post(SECRETARY_REST_API_URL,secretary,{ headers: authHeader() });
    }

    update(id,secretary){
        return axios.put(SECRETARY_REST_API_URL + '/edit-secretary/' + id,secretary,{ headers: authHeader() });
    }

    getSecretaryById(id){
        return axios.get(SECRETARY_REST_API_URL +"/"+ id,{ headers: authHeader() });
    }

    deleteSecretary(id){
        return axios.delete(SECRETARY_REST_API_URL + '/delete/' + id,{ headers: authHeader() });
    }

    viewAllPatientsList(){
        return axios.get(SECRETARY_REST_API_URL + '/patientList',{ headers: authHeader() });
    }

    viewSecretaryCalendar(){
        return axios.get(SECRETARY_REST_API_URL + '/appointment',{ headers: authHeader() });
    }

}

export default new SecretaryService();