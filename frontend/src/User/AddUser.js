import React, { useState} from 'react';
import {Table, Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useHistory} from "react-router-dom";



function AddAthlete() {

    axios.defaults.headers.common['Authorization'] = localStorage.getItem("token");
    const history = useHistory();

    const [user, setUser] = useState({});

    const sendUser=(e) =>{
        e.preventDefault();
        console.log(user)

        axios.post('/users', user)
            .catch((err) => {
                console.log(err);
            });
        history.push("/users");
    }


    return (
        <div>
            <h1>Add User</h1>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: e.target.value, password: user.password})}  type="email" placeholder={"email"}/>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: user.email, password: e.target.value})}  type="password" placeholder={"passowrd"}/>
                </Form.Group>
                <Button onClick={(e)=>sendUser(e)} variant="primary" type="submit">
                    Create
                </Button>
            </Form>
        </div>
    )
};


export default AddAthlete;