import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";


const Home = () => {
  const [content, setContent] = useState("");

  const myStyle={
    backgroundImage: 
    "url('https://cdn.pixabay.com/photo/2016/11/08/05/29/surgery-1807541_960_720.jpg')",
    height:'100vh',
    marginTop:'-70px',
    fontSize:'50px',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
  };

  useEffect(() => {
    UserService.getPublicContent().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();

        setContent(_content);
      }
    );
  }, []);

  return (
    <div style={myStyle}>
      <header className="jumbotron">
        <h3>{content}</h3>
      </header>
    </div>
  );
};

export default Home;