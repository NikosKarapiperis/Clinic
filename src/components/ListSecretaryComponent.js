import React, { useEffect, useState } from 'react'
import SecretaryService from '../services/SecretaryService'
import {Link} from 'react-router-dom'

const ListSecretaryComponent = () => {

  const [secretary, setSecretary] = useState([])

  useEffect(() => {
    getSecretary();
  }, [])

  const getSecretary = () =>{
    SecretaryService.getSecretary().then((response)=>{
      setSecretary(response.data)
      console.log(response.data)
    }).catch(error =>{
      console.log(error);
    })
  }

  const deleteSecretary = (id) =>{
    SecretaryService.deleteSecretary(id).then((response) =>{
        getSecretary();
    }).catch(error =>{
      console.log(error);
    })
  }
  

  return (
    <div className="container">
       <h2 className="text-center"> Secretary </h2>
       <Link to= "/add-secretary" className="btn btn-primary mb-2"> Add Secretary </Link>
       <table className="table table-bordered table-striped">
        <thead>
          <th> Secretary Id </th>
          <th> Secretary First Name </th>
          <th> Secretary Last Name </th>
          <th> Secretary Email </th>
          <th> Actions </th>
        </thead>
        <tbody>
          {
            secretary.map(
                secretary =>
              <tr key = {secretary.id}>
                <td> {secretary.id} </td>
                <td> {secretary.firstName} </td>
                <td> {secretary.lastName} </td>
                <td> {secretary.email} </td>
                <td>
                   <Link className="btn btn-info" to={`/edit-secretary/${secretary.id}`}> Update</Link>
                   <button className="btn btn-danger" onClick={()=>deleteSecretary(secretary.id)}
                    style = {{marginLeft:"10px"}}> Delete</button>
                </td>
              </tr>
            )
          }
        </tbody>
       </table>
    </div>
  )
}

export default ListSecretaryComponent