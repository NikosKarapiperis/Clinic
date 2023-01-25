import React, {useState} from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import PatientService from '../services/PatientService'

const AddAppointmentComponent = () => {

  const [date, setDate] = useState('')
  const [doctorSpeciality, setDoctorSpeciality] = useState('')
  const [appointmentType, setAppointmentType] = useState('')
  const [medicalDiagnosis, setMedicalDiagnosis] = useState('')
  const [medicinePrescribed, setMedicinePrescribed] = useState('')
  const history = useNavigate();
  const{id} = useParams();

  const saveAppointment = (e) =>{
    e.preventDefault();
    
    const appointment = {date,doctorSpeciality,appointmentType,medicalDiagnosis,medicinePrescribed}

    PatientService.bookAppointment(id,appointment).then((response)=>{
    console.log(response.data)
     history("/patient");
    }).catch(error => {
          console.log(error)
    })
  }


  return (
    <div>
        <br>
        </br>
        <div className="container">
            <div className="row">
              <div className="card col-md-6 offset-md-3 offset-md-3">
                <h2 className="text-center"> Add Appointment</h2>
                <div className="card-body">
                    <form>
                        <div className="form-group mb-2">
                            <label className="form-label"> Date :</label>
                            <input type="text" placeholder="Enter Date" name="date" className="form-control" value={date} onChange={(e)=> setDate(e.target.value)}></input>
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label"> Doctor Speciality :</label>
                            <input type="text" placeholder="Enter Doctor Speciality" name="doctorSpeciality" className="form-control" value={doctorSpeciality} onChange={(e)=> setDoctorSpeciality(e.target.value)}></input>
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label"> Appointment Type :</label>
                            <input type="text" placeholder="Enter Appointment Type" name="Appointment Type" className="form-control" value={appointmentType} onChange={(e)=> setAppointmentType(e.target.value)}></input>
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label"> Medical Diagnosis :</label>
                            <input type="text" placeholder="Enter Medical Diagnosis" name="Medical Diagnosis" className="form-control" value={medicalDiagnosis} onChange={(e)=> setMedicalDiagnosis(e.target.value)}></input>
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label"> Medicine Prescribed :</label>
                            <input type="text" placeholder="Enter Medicine Prescribed" name="Medicine Prescribed" className="form-control" value={medicinePrescribed} onChange={(e)=> setMedicinePrescribed(e.target.value)}></input>
                        </div>

                        <button className="btn btn-success" onClick = {(e)=> saveAppointment(e)}> Submit</button>
                        <Link to="/patient" className="btn btn-danger"> Cancel</Link>
                    </form>
                </div>
              </div>
            </div>
        </div>

    </div>
  )
}

export default AddAppointmentComponent