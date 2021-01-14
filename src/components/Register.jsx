import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import axios from 'axios';
export default class Register extends Component {
    componentDidMount() {
        window.scrollTo(0, 0)
    }
    constructor(props) {
        super(props);
        this.state = {
            custFirstName: '',
            custLastName: '',
            custEmail: '',
            custMobile: '',
            custPassword: '',
            errors: {}
        }
        this.handleChangeFirstName = this.handleChangeFirstName.bind(this);
        this.handleChangeLastName = this.handleChangeLastName.bind(this);
        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangeMobile = this.handleChangeMobile.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);

        this.submituserRegistrationForm = this.submituserRegistrationForm.bind(this);

    }
    handleChangeFirstName(e) {
        this.setState({ custFirstName: e.target.value });
    }
    handleChangeLastName(e) {
        this.setState({ custLastName: e.target.value });
    }
    handleChangeEmail(e) {

        this.setState({ custEmail: e.target.value });

    }

    handleChangeMobile(e) {

        this.setState({ custMobile: e.target.value });

    }

    handleChangePassword(e) {

        this.setState({ custPassword: e.target.value });

    }

    submituserRegistrationForm(e) {

        e.preventDefault();

        if (this.validateForm()) {

            console.log(this.state);

            var apiBaseUrl = "http://localhost:8081/springfox/api/customers/";

            var data = {
                "custFirstName": this.state.custFirstName,
                "custLastName": this.state.custLastName,
                "custEmail": this.state.custEmail,
                "custMobile": this.state.custMobile,
                "custPassword": this.state.custPassword

            }

            var headers = {

                'Content-Type': 'application/json',

            }

            console.log(data);

            axios.post(apiBaseUrl, data, { headers: headers }).then(function (response) {

                console.log(response);
                window.location = "/login"

                if (response.data.success) {

                    localStorage.setItem("u_code", encodeURIComponent(JSON.stringify(response.data.data)));

                    localStorage.setItem('is_done', true);

                    window.location.href = "/";

                    console.log("Register successfull");

                } else {

                    alert(response.data.message);

                }

            }).catch(function (error) {

                console.log(error);

            });

        }

    }

    validateForm() {

        let errors = {};

        let formIsValid = true;

        if (!this.state.custEmail) {

            formIsValid = false;

            errors["email"] = "*Please enter your email-ID.";

        }

        if (typeof this.state.custEmail !== "undefined") {

            //regular expression for email validation

            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);

            if (!pattern.test(this.state.custEmail)) {

                formIsValid = false;

                errors["email"] = "*Please enter valid email-ID.";

            }

        }

        if (!this.state.custMobile) {

            formIsValid = false;

            errors["mobileno"] = "*Please enter your mobile no.";

        }

        if (typeof this.state.custMobile !== "undefined") {

            if (!this.state.custMobile.match(/^[0-9]{10}$/)) {

                formIsValid = false;

                errors["mobileno"] = "*Please enter valid mobile no.";

            }

        }

        if (!this.state.custPassword) {

            formIsValid = false;

            errors["password"] = "*Please enter your password.";

        }

        if (typeof this.state.custPassword !== "undefined") {

            if (!this.state.custPassword.match(/^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/)) {

                formIsValid = false;

                errors["password"] = "*Please enter secure and strong password.";

            }

        }

        this.setState({

            errors: errors

        });

        return formIsValid;

    }

    render() {

        return (

            <div>

                <div className="container">

                    <div className="row">

                        <div className="col-md-4 login-sec">

                            <h2 className="text-center">Customer Registration</h2>

                            <Form method="post" name="userRegistrationForm" onSubmit={this.submituserRegistrationForm}>

                                <FormGroup>

                                    <Label for="exampleName">First Name</Label>

                                    <Input type="text" name="custFirstName" id="custFirstName" value={this.state.custFirstName} onChange={this.handleChangeFirstName} placeholder="Enter your first name" />

                                    <div className="errorMsg">{this.state.errors.name}</div>

                                </FormGroup>
                                <FormGroup>

                                    <Label for="exampleName">Last Name</Label>

                                    <Input type="text" name="custLastName" id="custLastName" value={this.state.custLastName} onChange={this.handleChangeLastName} placeholder="Enter your last name" />

                                    <div className="errorMsg">{this.state.errors.name}</div>

                                </FormGroup>

                                <FormGroup>

                                    <Label for="exampleEmail">Email</Label>

                                    <Input type="email" name="custEmail" id="custEmail" value={this.state.custEmail} onChange={this.handleChangeEmail} placeholder="Enter a email" />

                                    <div className="errorMsg">{this.state.errors.emailid}</div>

                                </FormGroup>

                                <FormGroup>

                                    <Label for="exampleMobile">Mobile No.</Label>

                                    <Input type="text" name="custMobile" id="custMobile" value={this.state.custMobile} onChange={this.handleChangeMobile} placeholder="Enter a Mobile No." />

                                    <div className="errorMsg">{this.state.errors.mobileno}</div>

                                </FormGroup>



                                <FormGroup>

                                    <Label for="examplePassword">Password</Label>

                                    <Input type="password" name="custPassword" id="custPassword" value={this.state.custPassword} onChange={this.handleChangePassword} placeholder="Enter a password" />

                                    <div className="errorMsg">{this.state.errors.password}</div>

                                </FormGroup>

                                <div className="d-flex justify-content-center mt-3 login_container">

                                    <Button type="submit" className="btn btn-login">Submit</Button>

                                </div>

                                <div className="mt-4">

                                    <div className="d-flex justify-content-center links">

                                        <Link href="/login" to="/login" className="linka">Already Account Login </Link>

                                    </div>

                                </div>

                            </Form>

                        </div>

                        <div className="col-md-8 banner-sec"></div>

                    </div>

                </div>

            </div>

        )

    }

}