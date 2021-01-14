import { Component } from 'react';
class Logout extends Component {
    render() {
        window.location = "/home";
        return localStorage.clear();
    }
}
export default Logout;