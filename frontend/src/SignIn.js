import React, {useState} from "react";
import axios from "axios";
import {Button, Form} from "react-bootstrap";
import {useHistory} from "react-router-dom";


function SignIn(){
    const history = useHistory();

    const [user, setUser] = useState({
        email:"",
        password: ""
    });

    const sendUser=(e) =>{
        e.preventDefault();

        axios.post('/auth/login', user)
            .then((res) => {
                if (res.data){
                    localStorage.setItem("token",res.data);
                    history.push("/users")
                }
            })
            .catch((err) => {
                console.log(err);
            });
    }

    return (
        <div>
            <h1>Sign In</h1>
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
            <p className="forgot-password text-right">
                No Account? Sign up for Free <a href="http://localhost:3000/signUP?#">sign up?</a>
            </p>
        </div>
    );
}
export default SignIn;