import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import DoctorService from '../services/DoctorService'
import PatientService from '../services/PatientService'

const ListDoctorsForSelectOneComponent = () => {

  const [doctors, setDoctors] = useState([])
  const{id} = useParams();
  const history = useNavigate();

  useEffect(() => {
    getAllDoctors();
  }, [])

  const getAllDoctors = () =>{
    DoctorService.getAllDoctors().then((response)=>{
      setDoctors(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const addDoctor = (id,doctors) =>{
    PatientService.addDoctor(id,doctors).then((response) =>{
     history("/patient")
    }).catch(error =>{
      console.log(error);
    })
  }

  

  return (
    <div className="container">
       <h2 className="text-center"> List Doctors</h2>
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
                   <button className="btn btn-primary" onClick={()=>addDoctor(id,doctors)}
                    style = {{marginLeft:"10px"}}> Select Doctor</button>
                </td>
              </tr>
            )
          }
        </tbody>
       </table>
    </div>
  )
}

export default ListDoctorsForSelectOneComponent