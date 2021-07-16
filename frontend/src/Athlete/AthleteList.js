import React, {Component, useEffect, useState} from 'react';
import {Table, Button, Form} from 'react-bootstrap';
import axios from "axios";
import {Link} from "react-router-dom";

function AthleteList() {

    axios.defaults.headers.common['Authorization'] = localStorage.getItem("token");

    const [athletes, setAthletes] = useState([]);

    const getAthletes = () => {
        axios.get("/athletes")
            .then((res) => {
                setAthletes(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    useEffect( () => {
        getAthletes()
    }, [athletes]);


    const deleteAthlete = (id) => {
        axios.delete("/athletes/" + id)
            .catch((err) => {
                console.log(err);
            });
        getAthletes();
        window.location.reload();
    }

    const athleteList = athletes.map(athlete => {
        return <Table striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Nickname</th>
                <th>Age</th>
                <th>Sport</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{athlete.id}</td>
                <td>{athlete.name}</td>
                <td>{athlete.nickname}</td>
                <td>{athlete.age}</td>
                <td>{athlete.sport.name}</td>
                <td>
                    <Link to={"/editAthlete/"+athlete.id} className="btn btn-warning">Update</Link>
                </td>
                <td>
                    <Button  variant="danger" onClick={() => deleteAthlete(athlete.id)}>Delete</Button>
                </td>
            </tr>
            </tbody>
        </Table>
    });
    return (
        <div>
            {athleteList}
            <div className="float-right">
                <Link to={"/addAthlete"} className="btn btn-primary  btn-lg btn-block">Add athlete</Link>
            </div>
            <br/>
            <div className="float-right">
                <Link  to={"/users"} className="btn btn-info  btn-lg btn-block">Go to users</Link>
            </div>
        </div>
    )};


export default AthleteList;