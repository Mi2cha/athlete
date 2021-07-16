import React, {Component, useEffect, useState} from 'react';
import {Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useHistory, useParams} from "react-router-dom";


function AddAthlete() {

    axios.defaults.headers.common['Authorization'] = localStorage.getItem("token");
    const history = useHistory();
    const {id} = useParams();

    const [athlete, setAthlete] = useState({});

    const sendAthlete = (e) => {
        setAthlete(
            {
                name: athlete.name,
                nickname:e.target.value,
                age: athlete.age
            })
        e.preventDefault();
        console.log(athlete)
        axios.put('/athletes/'+id, athlete)
            .catch((err) => {
                console.log(err);
            });
        history.push("/athletes");
    }

    useEffect(() => {
        console.log(id)
        axios.get("/athletes/"+id)
            .then((res) => {
                setAthlete(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);

    return (
        <div>
            <h1>Edit Athlete</h1>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Name</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: e.target.value,
                            nickname: athlete.nickname,
                            age: athlete.age
                        })}
                                  type="text" value={athlete.name}/>
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Nickname</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: athlete.name,
                            nickname:e.target.value,
                            age: athlete.age
                        })}
                                  type="text" value={athlete.nickname}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Age</Form.Label>
                    <Form.Control onChange={(e) => setAthlete(
                        {
                            name: athlete.name,
                            nickname: athlete.nickname,
                            age: e.target.value
                        })}
                                  type="number" value={athlete.age}/>
                </Form.Group>
                <Button onClick={(e) => sendAthlete(e)} variant="primary" type="submit">
                    Create
                </Button>
            </Form>
        </div>
    )
};


export default AddAthlete;