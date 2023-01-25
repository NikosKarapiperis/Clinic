import React, {useState, useEffect} from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import SecretaryService from '../services/SecretaryService'

const AddSecretaryComponent = () => {

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const history = useNavigate();
  const{id} = useParams();

  const saveOrUpdateSecretary = (e) =>{
    e.preventDefault();
    
    const secretary = {firstName,lastName,email}

    if(id){
        SecretaryService.update(id,secretary).then((response)=>{
         console.log(response.data)

         history("/secretary");
       }).catch(error => {
          console.log(error)
      })

    }else{
        SecretaryService.save(secretary).then((response)=>{
        console.log(response.data)
        
        history("/secretary");
  
      }).catch(error => {
          console.log(error)
      })
    }
    
  
  }

  useEffect(()=>{
    if(id){
      SecretaryService.getSecretaryById(id).then((response)=>{
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
      return <h2 className="text-center"> Update Secretary</h2>
    }else{
      return <h2 className="text-center"> Add Secretary</h2>
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

                        <button className="btn btn-success" onClick = {(e)=> saveOrUpdateSecretary(e)}> Submit</button>
                        <Link to="/doctors" className="btn btn-danger"> Cancel</Link>
                    </form>
                </div>
              </div>
            </div>
        </div>

    </div>
  )
}

export default AddSecretaryComponent