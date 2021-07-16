import React, {useState} from "react";
import axios from "axios";
import {Button, Form} from "react-bootstrap";
import {useHistory} from "react-router-dom";


function SignUp(){

    const history = useHistory();

    const [user, setUser] = useState({
        email:"",
        password: ""
    });

    const sendUser=(e) =>{
        e.preventDefault();

        console.log(user)

        axios.post('/auth/registerUser', user)
            .catch((err) => {
                console.log(err);
            });
        history.push("/users")
    }

    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: e.target.value, password: user.password})} type="email" placeholder="Enter email"/>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e)=>setUser({email: user.email, password: e.target.value})} type="password" placeholder="Password"/>
                </Form.Group>
                <Button variant="primary" type="submit" onClick={(e)=>sendUser(e)}>
                    Submit
                </Button>
            </Form>
        </div>
    );
}
export default SignUp;