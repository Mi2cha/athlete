import React, {Component, useEffect, useState} from 'react';
import {Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useHistory} from "react-router-dom";


function AddAthlete() {
    const history = useHistory();
    axios.defaults.headers.common['Authorization'] = localStorage.getItem("token");

    const [athlete, setAthlete] = useState({});

    const sendAthlete = (e) => {
        e.preventDefault();
        console.log(athlete)
        axios.post('/athletes', athlete)
            .catch((err) => {
                console.log(err);
            });
        history.push("/athletes");
    }


    return (
        <div>
            <h1>Add Athlete</h1>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Name</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: e.target.value,
                            nickname: athlete.nickname,
                            age: athlete.age,
                            sport: athlete.sport
                        })}
                                  type="text" placeholder={"name"}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Nickname</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: athlete.name,
                            nickname:e.target.value,
                            age: athlete.age,
                            sport: athlete.sport
                        })}
                                  type="text" placeholder={"Nickname"}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Age</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: athlete.name,
                            nickname: athlete.nickname,
                            age: e.target.value,
                            sport: athlete.sport
                        })}
                                  type="number" placeholder={"age"}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Sport</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: athlete.name,
                            nickname: athlete.nickname,
                            age: athlete.age,
                            sport: e.target.value
                        })}
                                  type="text" placeholder={"Sport"}/>
                </Form.Group>
                <Button onClick={(e) => sendAthlete(e)} variant="primary" type="submit">
                    Create
                </Button>
            </Form>
        </div>
    )
};


export default AddAthlete;