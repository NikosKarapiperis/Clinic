import React, { useEffect, useState } from 'react'
import PatientService from '../services/PatientService'
import {Link, useParams} from 'react-router-dom'
import AuthService from "../services/auth.service";
import DoctorService from '../services/DoctorService'
import SecretaryService from '../services/SecretaryService'

const ListPatientComponent = () => {

  const [patient, setPatient] = useState([])
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const[showPatient, setShowPatient] = useState(false);
  // eslint-disable-next-line no-unused-vars
  const[showDoctor, setShowDoctor] = useState(false);
  const[showSecretary, setShowSecretary] = useState(false);
  const{id} = useParams();

  useEffect(() => {

    const user = AuthService.getCurrentUser();
    if (user) {
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
      setShowPatient(user.roles.includes("ROLE_PATIENT"));
      setShowDoctor(user.roles.includes("ROLE_DOCTOR"));
      setShowSecretary(user.roles.includes("ROLE_SECRETARY"));
    }

   if(showDoctor){
    getAllPatientDoctor(id);
  }else if(showPatient || showAdminBoard){
    getAllPatient();
  }else if(showSecretary){
     getAllPatientSecretary();
  }    
    

  }, [id, showDoctor, showPatient, showSecretary, showAdminBoard])

  const getAllPatient = () =>{
    PatientService.getAllPatient().then((response)=>{
      setPatient(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const getAllPatientDoctor = (id) =>{
    DoctorService.viewMyPatientsList(id).then((response)=>{
      setPatient(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const getAllPatientSecretary = () =>{
    SecretaryService.viewAllPatientsList().then((response)=>{
      setPatient(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }


  const deletePatient = (id) =>{
    PatientService.deletePatient(id).then((response) =>{
        getAllPatient();
    }).catch(error =>{
      console.log(error);
    })
  }
  

  return (
    <div className="container">
       <h2 className="text-center"> List Patients</h2>
       {showAdminBoard &&(<Link to= "/add-patient" 
                          className="btn btn-primary mb-2"> Add Patient </Link>
       )}
       <table className="table table-bordered table-striped">
        <thead>
          <th> Patient Id </th>
          <th> Patient First Name </th>
          <th> Patient Last Name </th>
          <th> Patient Email </th>
          {(showAdminBoard || showPatient || showSecretary) &&(<th> Actions </th>
          )}
        </thead>
        <tbody>
          {
            patient.map(
              patient =>
              <tr key = {patient.id}>
                <td> {patient.id} </td>
                <td> {patient.firstName} </td>
                <td> {patient.lastName} </td>
                <td> {patient.email} </td>
                <td>
                {showAdminBoard && (<Link className="btn btn-info" 
                                     to={`/edit-patient/${patient.id}`}> Update</Link>
                )}
                {showAdminBoard &&  (<button className="btn btn-danger" onClick={()=>deletePatient(patient.id)}
                    style = {{marginLeft:"20px"}}> Delete</button>
                )}
                {showPatient && (<Link className="btn btn-info" 
                                 to={`/${patient.id}/doctors`}> Select Doctor</Link>
                )}
                {showPatient && (<Link className="btn btn-info" 
                   style = {{marginLeft:"10px"}} to={`/${patient.id}/add-appointment`}> Add Appointment</Link>
                )}
                {showPatient && (<Link className="btn btn-info" 
                   style = {{marginLeft:"10px"}} to={`/${patient.id}/appointment`}> List Appointment</Link>
                )}
                {showSecretary && (<Link className="btn btn-info" 
                   style = {{marginLeft:"10px"}} to={`/${patient.id}/add-appointment`}> Add Appointment</Link>
                )}
                {showSecretary && (<Link className="btn btn-info" 
                   style = {{marginLeft:"10px"}} to={`/${patient.id}/appointment`}> List Appointment</Link>
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

export default ListPatientComponent
