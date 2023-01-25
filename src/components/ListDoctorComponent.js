import React, { useEffect, useState } from 'react'
import DoctorService from '../services/DoctorService'
import {Link} from 'react-router-dom'
import AuthService from "../services/auth.service";

const ListDoctorComponent = () => {

  const [doctors, setDoctors] = useState([])
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const[showDoctor, setShowDoctor] = useState(false);
  

  useEffect(() => {
    getAllDoctors();

    const user = AuthService.getCurrentUser();
    if (user) {
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
      setShowDoctor(user.roles.includes("ROLE_DOCTOR"));
    }
  }, [])

  const getAllDoctors = () =>{
    DoctorService.getAllDoctors().then((response)=>{
      setDoctors(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const deleteDoctor = (id) =>{
    DoctorService.deleteDoctor(id).then((response) =>{
     getAllDoctors();
    }).catch(error =>{
      console.log(error);
    })
  }
  

  return (
    <div className="container">
       <h2 className="text-center"> List Doctors</h2>
       {showAdminBoard && (<Link to= "/add-doctor" 
                           className="btn btn-primary mb-2"> Add Doctor </Link>
       )}
       <table className="table table-bordered table-striped">
        <thead>
          <th> Doctor Id </th>
          <th> Doctor First Name </th>
          <th> Doctor Last Name </th>
          <th> Doctor Email </th>
          <th> Doctor Specialty </th>
          <th> Actions </th>
        </thead>
        <tbody>
          {
            doctors.map(
              doctors =>
              <tr key = {doctors.id}>
                <td> {doctors.id} </td>
                <td> {doctors.firstName} </td>
                <td> {doctors.lastName} </td>
                <td> {doctors.email} </td>
                <td> {doctors.specialty} </td>
                <td>
                  {showAdminBoard && ( <Link className="btn btn-info"
                                       to={`/edit-doctor/${doctors.id}`}> Update</Link>
                  )}
                   {showAdminBoard && ( <button className="btn btn-danger" onClick={()=>deleteDoctor(doctors.id)}
                                          style = {{marginLeft:"10px"}}> Delete</button>
                   )}
                   {showDoctor && (<Link className="btn btn-info" 
                                   style = {{marginLeft:"10px"}} to={`/${doctors.id}/appointment`}> List Appointment</Link>
                   )}
                   {showDoctor && (<Link className="btn btn-info" 
                                   style = {{marginLeft:"10px"}} to={`/${doctors.id}/patient`}> List Patient</Link>
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

export default ListDoctorComponent