import axios from 'axios'
import authHeader from "./auth-header";

const PATIENT_REST_API_URL = 'http://localhost:8080/patient';

class PatientService{

    getAllPatient(){
        return axios.get(PATIENT_REST_API_URL + '/getAll',{ headers: authHeader() });
    }

    save(patient){
        return axios.post(PATIENT_REST_API_URL,patient,{ headers: authHeader() });
    }

    update(id,patient){
        return axios.put(PATIENT_REST_API_URL + '/edit-patient/' + id,patient,{ headers: authHeader() });
    }

    getPatientById(id){
        return axios.get(PATIENT_REST_API_URL +"/"+ id,{ headers: authHeader() });
    }

    deletePatient(id){
        return axios.delete(PATIENT_REST_API_URL + '/delete/' + id,{ headers: authHeader() });
    }

    addDoctor(id,doctor){
        return axios.post(PATIENT_REST_API_URL + '/' + id + '/doctors',doctor,{ headers: authHeader() });
    }

    viewPatientCalendar(id){
        return axios.get(PATIENT_REST_API_URL + '/' + id + '/MyAppointment', { headers: authHeader() });
    }

    cancelAppointment(pid,aid){
        return axios.delete(PATIENT_REST_API_URL + '/' + pid + '/cancelAppointment/' + aid, { headers: authHeader() });
    }

    bookAppointment(id,appointment){
        return axios.post(PATIENT_REST_API_URL + '/' + id + '/appointment',appointment, { headers: authHeader() })
    }

}

export default new PatientService();