import React, {useState, useEffect} from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import PatientService from '../services/PatientService'

const AddPatientComponent = () => {

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const history = useNavigate();
  const{id} = useParams();

  const saveOrUpdatePatient = (e) =>{
    e.preventDefault();
    
    const patient = {firstName,lastName,email}

    if(id){
       PatientService.update(id,patient).then((response)=>{
         console.log(response.data)

         history("/patient");
       }).catch(error => {
          console.log(error)
      })

    }else{
        PatientService.save(patient).then((response)=>{
        console.log(response.data)
        
        history("/patient");
  
      }).catch(error => {
          console.log(error)
      })
    }
    
  
  }

  useEffect(()=>{
    if(id){
      PatientService.getPatientById(id).then((response)=>{
      setFirstName(response.data.firstName)
      setLastName(response.data.lastName)
      setEmail(response.data.email)
     }).catch(error =>{
        console.log(error)
     })
    }
  },[id]);

  const title = () => {
    if(id){
      return <h2 className="text-center"> Update Patient</h2>
    }else{
      return <h2 className="text-center"> Add Patient</h2>
    }
  }

  return (
    <div>
        <br>
        </br>
        <div className="container">
            <div className="row">
              <div className="card col-md-6 offset-md-3 offset-md-3">
                {
                  title()
                }
                <div className="card-body">
                    <form>
                        <div className="form-group mb-2">
                            <label className="form-label"> First Name :</label>
                            <input type="text" placeholder="Enter First Name" name="firstName" className="form-control" value={firstName} onChange={(e)=> setFirstName(e.target.value)}></input>
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label"> Last Name :</label>
                            <input type="text" placeholder="Enter Last Name" name="lastName" className="form-control" value={lastName} onChange={(e)=> setLastName(e.target.value)}></input>
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label"> Email :</label>
                            <input type="text" placeholder="Enter Email" name="Email" className="form-control" value={email} onChange={(e)=> setEmail(e.target.value)}></input>
                        </div>

                        <button className="btn btn-success" onClick = {(e)=> saveOrUpdatePatient(e)}> Submit</button>
                        <Link to="/patient" className="btn btn-danger"> Cancel</Link>
                    </form>
                </div>
              </div>
            </div>
        </div>

    </div>
  )
}

export default AddPatientComponent