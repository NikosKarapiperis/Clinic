import axios from 'axios'
import authHeader from "./auth-header";

const DOCTOR_REST_API_URL = 'http://localhost:8080/doctors/';

class DoctorService{

    getAllDoctors(){
        return axios.get(DOCTOR_REST_API_URL + 'getAll',{ headers: authHeader() });
    }

    save(doctor){
        return axios.post(DOCTOR_REST_API_URL + 'saveDoctor',doctor,{ headers: authHeader() });
    }

    update(id,doctor){
        return axios.put(DOCTOR_REST_API_URL + 'edit-doctor/' + id,doctor,{ headers: authHeader() });
    }

    getDoctorById(id){
        return axios.get(DOCTOR_REST_API_URL + id,{ headers: authHeader() });
    }

    deleteDoctor(id){
        return axios.delete(DOCTOR_REST_API_URL + 'delete/' + id,{ headers: authHeader() });
    }

    viewDoctorCalendar(id){
        return axios.get(DOCTOR_REST_API_URL + id + '/MyAppointment', { headers: authHeader() });
    }

    viewMyPatientsList(id){
        return axios.get(DOCTOR_REST_API_URL + id + '/MyPatients', { headers: authHeader() });
    }

}

export default new DoctorService();