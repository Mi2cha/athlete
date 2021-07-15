import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import UserList from "./UserList";
import EditUser from "./editUser";
import SignUp from "./SignUp";
import SignIn from "./SignIn";

function App() {
    return (
        <div>
            <Router>
                <Switch>
                    <Route path={'/editUser'} component={EditUser}/>
                    <Route path={'/users'} component={UserList}/>
                    <Route path={'/signUp'} component={SignUp}/>
                    <Route path={'/'} component={SignIn}/>
                </Switch>
            </Router>
        </div>
    );
}

export default App;