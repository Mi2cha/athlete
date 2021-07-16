import React, {Component, useEffect, useState} from 'react';
import {Table, Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useParams, useHistory} from "react-router-dom";



function EditUser() {
    const history = useHistory();

    const {id} = useParams();

    const [user, setUser] = useState({});

    const sendUser=(e) =>{
        e.preventDefault();

        console.log(user)

        axios.put('/users/'+id, user)
            .catch((err) => {
                console.log(err);
            });
        history.push("/users");
    }

    useEffect(() => {
        console.log(id)
        axios.get("/users/"+id)
            .then((res) => {
                setUser(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);
    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: e.target.value, password: user.password})}  type="email" value={user.email}/>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: user.email, password: e.target.value})}  type="password" value={user.password}/>
                </Form.Group>
                <Button onClick={(e)=>sendUser(e)} variant="primary" type="submit">
                    Change
                </Button>
            </Form>
        </div>
    )
};


export default EditUser;