import React from 'react';
import { BrowserRouter as Router, Route,Switch } from 'react-router-dom';
import Login from './components/Login';
import DashBoard from './components/Dashboard';
import Register from './components/Register';
import Logout from './components/Logout';
import Navbar from './components/Navbar';
import './App.css';
import Home from './pages/Home';
import Electronic from './components/Electronic';
import Restaurant from './components/Restaurant';
import Hotel from './components/Hotel';
import Jobs from './components/Jobs';
import ContactUs from './pages/ContactUs';
import LoginInitial from './components/LoginInitial';

class App extends React.Component {
render() {
return (
<Router>
<Navbar />
<Switch>
    <Route path='/' exact component={Home} />
          <Route path='/electronic' component={Electronic} />
          <Route path='/restaurant' component={Restaurant} />
          <Route path='/hotel' component={Hotel} />
          <Route path='/jobs' component={Jobs} />
          <Route path='/home' component={Home} />
          <Route path='/contactUs' component={ContactUs} />
<Route path="/Login" component={Login} />
<Route path="/Dashboard" component={DashBoard}/>
<Route path="/Register" component={Register}/>
<Route path="/Logout" component={Logout}/>
<Route path='/sign-up' component={LoginInitial} />
</Switch>
</Router>
);

}

}
export default App;






