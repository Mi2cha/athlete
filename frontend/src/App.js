import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import UserList from "./User/UserList";
import EditUser from "./User/editUser";
import SignUp from "./SignUp";
import SignIn from "./SignIn";
import AddUser from "./User/AddUser";
import EditAthlete from "./Athlete/EditAthlete";
import AthleteList from "./Athlete/AthleteList";
import AddAthlete from "./Athlete/AddAthlete";

function App() {
    return (
        <div>
            <Router>
                <Switch>
                    <Route path="/editUser/:id" component={EditUser}/>
                    <Route path={'/users'} component={UserList}/>
                    <Route path={'/addUser'} component={AddUser}/>
                    <Route path={'/signUp'} component={SignUp}/>
                    <Route path="/editAthlete/:id" component={EditAthlete}/>
                    <Route path={'/athletes'} component={AthleteList}/>
                    <Route path={'/addAthlete'} component={AddAthlete}/>
                    <Route path={'/'} component={SignIn}/>

                </Switch>
            </Router>
        </div>
    );
}

export default App;