import './App.css';
import {BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';

import React, { useState, useEffect } from "react";


import HeaderComponent from './components/HeaderComponent';
import ListDoctorComponent from './components/ListDoctorComponent';
import AddDoctorComponent from './components/AddDoctorComponent';
import ListPatientComponent from './components/ListPatientComponent';
import AddPatientComponent from './components/AddPatientComponent';
import ListSecretaryComponent from './components/ListSecretaryComponent';
import AddSecretaryComponent from './components/AddSecretaryComponent.js';
import AuthService from "./services/auth.service";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";
import BoardAdmin from "./components/BoardAdmin";
import ListDoctorsForSelectOneComponent from './components/ListDoctorsForSelectOneComponent';
import ListAppointmentComponent from './components/ListAppointmentComponent';
import AddAppointmentComponent from './components/AddAppointmentComponent';

const App = () => {

  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);
  const[showPatient, setShowPatient] = useState(false);
  const[showDoctor, setShowDoctor] = useState(false);
  const[showSecretary, setShowSecretary] = useState(false);


  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
      setShowPatient(user.roles.includes("ROLE_PATIENT"));
      setShowDoctor(user.roles.includes("ROLE_DOCTOR"));
      setShowSecretary(user.roles.includes("ROLE_SECRETARY"));
    }
  }, []);

  const logOut = () => {
    AuthService.logout();
  };


  

  return (
   
    <Router>
    <div>
      
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          Hua
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/home"} className="nav-link">
              Home
            </Link>
          </li>

          

          {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/doctors"} className="nav-link">
                Doctors
              </Link>
            </li>
          )}

        {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/add-doctor"} className="nav-link">
                Add Doctor
              </Link>
            </li>
          )}

        {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/patient"} className="nav-link">
                Patient
              </Link>
            </li>
          )}

        {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/add-patient"} className="nav-link">
                Add Patient
              </Link>
            </li>
          )}

        {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/secretary"} className="nav-link">
                Secretary
              </Link>
            </li>
          )}

          {showAdminBoard && (
             <li className="nav-item">
             <Link to={"/register"} className="nav-link">
               Sign Up
             </Link>
           </li>
          )}

        {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/add-secretary"} className="nav-link">
                Add Secretary
              </Link>
            </li>
          )}


        {showPatient && (
            <li className="nav-item">
              <Link to={"/patient"} className="nav-link">
                Patient
              </Link>
            </li>
          )}

        {showDoctor && (
            <li className="nav-item">
              <Link to={"/doctors"} className="nav-link">
                Doctor
              </Link>
            </li>
          )}

        {showSecretary && (
            <li className="nav-item">
              <Link to={"/patient"} className="nav-link">
                Patients
              </Link>
            </li>
          )}

          {showSecretary && (
            <li className="nav-item">
              <Link to={"/appointment"} className="nav-link">
                Appointments
              </Link>
            </li>
          )}
       
        
        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                LogOut
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>
          </div>
        )}
      </nav>
    



    <div>
      
      
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/home" element={<Home/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/profile" element={<Profile/>} />
          <Route path="/user" element={<BoardUser/>} />
          <Route path="/admin" element={<BoardAdmin/>} />
          <Route index element={<Home />} />
          <Route path="/doctors" element={<ListDoctorComponent />}></Route>
          <Route path="/add-doctor" element={<AddDoctorComponent />}></Route>
          <Route path="/edit-doctor/:id" element={<AddDoctorComponent />}></Route>
          <Route path="/patient" element={<ListPatientComponent />}></Route>
          <Route path="/add-patient" element={<AddPatientComponent />}></Route>
          <Route path="/edit-patient/:id" element={<AddPatientComponent />}></Route>
          <Route path="/secretary" element={<ListSecretaryComponent />}></Route>
          <Route path="/add-secretary" element={<AddSecretaryComponent />}></Route>
          <Route path="/edit-secretary/:id" element={<AddSecretaryComponent />}></Route>
          <Route path="/:id/doctors" element={<ListDoctorsForSelectOneComponent />}></Route>
          <Route path="/:id/appointment" element={<ListAppointmentComponent />}></Route>
          <Route path="/:id/add-appointment" element={<AddAppointmentComponent />}></Route>
          <Route path=":id/patient" element={<ListPatientComponent />}></Route>
          <Route path="/appointment" element={<ListAppointmentComponent />}></Route>
      </Routes>
      
    </div>
  </div>
  </Router>
  );
  };


export default App;
