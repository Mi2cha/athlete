import React, {Component, useEffect, useState} from 'react';
import {Table, Button, Form} from 'react-bootstrap';
import axios from "axios";
import {Link} from "react-router-dom";
import {useParams} from "react-router-dom";


function EditUser() {
    let id = useParams();

    const [user, setUser] = useState({
    });

    const sendUser=(e) =>{
        e.preventDefault();

        console.log(user)

        axios.post('/users', user)
            .catch((err) => {
                console.log(err);
            });
    }

    useEffect(() => {
        axios.get("/users/")
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
                    <Form.Control onChange={(e)=>setUser({email: e.target.value, password: user.password})}  type="email" placeholder="Enter email"/>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: e.target.value, password: user.password})}  type="password" placeholder="Password"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Check me out"/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Change
                </Button>
            </Form>
        </div>
    )
};


export default EditUser;