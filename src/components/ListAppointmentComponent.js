import React, {useEffect,  useState } from 'react'
import PatientService from '../services/PatientService'
import { useParams, useNavigate} from 'react-router-dom'
import DoctorService from '../services/DoctorService'
import AuthService from "../services/auth.service";
import SecretaryService from '../services/SecretaryService'

const ListAppointmentComponent = () => {

  const [appointment, setAppointment] = useState([])
  const{id} = useParams();
  const history = useNavigate()
  const [showPatient, setShowPatient] = useState(false);
  const[showDoctor, setShowDoctor] = useState(false);
  const[showSecretary, setShowSecretary] = useState(false);
  

  useEffect(() => {

    const user = AuthService.getCurrentUser();
    if (user) {
      setShowDoctor(user.roles.includes("ROLE_DOCTOR"));
      setShowPatient(user.roles.includes("ROLE_PATIENT"));
      setShowSecretary(user.roles.includes("ROLE_SECRETARY"));
    }
    if(showPatient){
      getAllAppointments(id)
    }else if(showDoctor){
      getAllAppointmentsDoctors(id)
    }else if(showSecretary){
      getAllAppointmentsSecretary();
    }

  },[id,showPatient, showDoctor, showSecretary])

  const getAllAppointments = (id) =>{
    PatientService.viewPatientCalendar(id).then((response)=>{
      setAppointment(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const getAllAppointmentsDoctors = (id) =>{
    DoctorService.viewDoctorCalendar(id).then((response)=>{
      setAppointment(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const getAllAppointmentsSecretary = () =>{
    SecretaryService.viewSecretaryCalendar().then((response)=>{
      setAppointment(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }


  const deleteAppointment = (id,aid) =>{
    PatientService.cancelAppointment(id,aid).then((response) =>{
      if(showPatient){
        getAllAppointments(id);
      }else if(showSecretary){
        history("/appointment");
      }
    }).catch(error =>{
      console.log(error);
    })
  }
  

  return (
    <div className="container">
       <h2 className="text-center"> List Appointments</h2>
       <table className="table table-bordered table-striped">
        <thead>
          <th> Appointment Id </th>
          <th> Appointment Date </th>
          <th> Appointment Doctor Speciality </th>
          <th> Appointment Type </th>
          <th> Appointment Medical Diagnosis </th>
          <th> Appointment Medicine Prescribed </th>
          { (showPatient || showSecretary) && (<th> Actions </th>)}
        </thead>
        <tbody>
          {
            appointment.map(
              appointment =>
              <tr key = {appointment.id}>
                <td> {appointment.id} </td>
                <td> {appointment.date} </td>
                <td> {appointment.doctorSpeciality} </td>
                <td> {appointment.appointmentType} </td>
                <td> {appointment.medicalDiagnosis} </td>
                <td> {appointment.medicinePrescribed} </td>
                <td>
                  { showPatient && ( <button className="btn btn-danger" onClick={()=>deleteAppointment(id,appointment.id)}
                    style = {{marginLeft:"10px"}}> Delete</button>
                  )}
                  { showSecretary && ( <button className="btn btn-danger" onClick={()=>deleteAppointment(id,appointment.id)}
                    style = {{marginLeft:"10px"}}> Delete</button>
                  )}
                </td>
              </tr>
            )
          }
        </tbody>
       </table>
    </div>
  )
}

export default ListAppointmentComponent