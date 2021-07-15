import React, {Component, useEffect, useState} from 'react';
import {Table, Button, Form} from 'react-bootstrap';
import axios from "axios";
import {Link} from "react-router-dom";

function UserList() {

    const [users, setUsers] = useState([]);

    const getUsers = () => {
        axios.get("/users")
            .then((res) => {
                setUsers(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    useEffect( () => {
        getUsers()
    }, []);


    const deleteUser = (id) => {
        axios.delete("/users/" + id)
            .catch((err) => {
                console.log(err);
            });
        getUsers();
       window.location.reload();
    }

    const userList = users.map(user => {
        return <Table striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>Email</th>
                <th>Password</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{user.id}</td>
                <td>{user.email}</td>
                <td>{user.password}</td>
                <td>
                    <Link to={"/editUser/"+user.id} className="btn btn-warning">Update</Link>
                </td>
                <td>
                    <Button  variant="danger" onClick={() => deleteUser(user.id)}>Delete</Button>
                </td>
            </tr>
            </tbody>
        </Table>
    });
    return (
        <div>
            <div className="float-right">
                <Button color="success">Add User</Button>
            </div>
            {userList}
        </div>
    )};


export default UserList;