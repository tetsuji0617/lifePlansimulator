import React, { Component } from 'react'
//import Chart from 'chart.js/auto';
import { Bar } from 'react-chartjs-2';
import ValueInput from './components/ValueInput'

class App2 extends Component {

    constructor(props) {
        super(props)
        console.log('constructor')
        console.log(props)
        this.state = {
            loading: false,
            changeState: false,
            age: 30,
            income: 500,
            expense: 20
        }
    }

    componentDidMount() {
        console.log('componentDidMount')
        //        fetch('http://localhost:8080/api/getLifePlan', { method: 'GET' })
        //            .then(response => response.json())
        //            .then(responseData => {
        //                this.setState({
        //                    loading: true,
        //                    data: responseData
        //                })
        //            }).catch((error) => {
        //                console.error(error);
        //            });;
        //        console.log('componentWillUpdate')
        //        console.log(this.state.income)
        //        console.log(this.state.age)
        //        console.log(this.state.expense)
        //        const requestOptions = {
        //            method: 'POST',
        //            headers: { 'Content-Type': 'application/json' },
        //            body: JSON.stringify(this.state)
        //        }
        //        fetch('http://localhost:8080/api/getLifePlan2', requestOptions)
        //            .then((response) => response.json())
        //            .then((responseJson) => {
        //                this.setState({
        //                    loading: true,
        //                    data: responseJson
        //                })
        //               console.log(responseJson)
        //            })
        //        console.log(this.state.data)
        //
    }

    inputChange(e) {
        const inputValue = e.target.value
        const inputKey = e.target.name
        this.setState({
            [inputKey]: inputValue
        })
    }

    ageChanged(e) {
        const ageValue = e.value
        this.setState({
            age: ageValue
        })
    }
    incomeChanged(e) {
        const incomeValue = e.value
        this.setState({
            income: incomeValue
        })
    }

    requestApi() {
        console.log('requestApi')
        console.log(this.state.income)
        console.log(this.state.age)
        console.log(this.state.expense)
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.state)
        }
        fetch('http://localhost:8080/api/getLifePlan2', requestOptions)
            .then((response) => response.json())
            .then((responseJson) => {
                this.setState({
                    loading: true,
                    data: responseJson
                })
                console.log(responseJson)
            })
        console.log(this.state.data)
        return this.state.data

    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log('shouldComponentUpdate')
        console.log(nextProps)
        console.log(nextState)
        if (this.state === nextState) {
            return false
        }
        return true
    }

    //    componentWillUpdate() {
    //         console.log('componentWillUpdate')
    //        console.log(this.state.income)
    //        console.log(this.state.age)
    //        console.log(this.state.expense)
    //        const requestOptions = {
    //            method: 'POST',
    //            headers: { 'Content-Type': 'application/json' },
    //            body: JSON.stringify(this.state)
    //        }
    //        fetch('http://localhost:8080/api/getLifePlan2', requestOptions)
    //            .then((response) => response.json())
    //            .then((responseJson) => {
    //                this.setState({
    //                    loading: true,
    //                    data: responseJson
    //                })
    //                console.log(responseJson)
    //            })
    //        console.log(this.state.data)
    //   }

    render() {
        console.log('render')
        //const datasetData = () => this.requestApi()
        //console.log(datasetData)
        console.log(this.state.age)
        console.log(this.state.income)
        if (this.state.loading) {
            return (
                <div>
                    <div onLoad={() => this.requestApi()}>
                        <ValueInput title='age'
                            onChange={e => this.ageChanged(e)}
                            value={this.state.age} />
                        <ValueInput title='income'
                            onChange={e => this.incomeChanged(e)}
                            value={this.state.income} />
                        <ValueInput title='expense'
                            onChange={e => this.inputChange(e)}
                            value={this.state.expense} />
                        <Bar data={this.state.data}
                            width={300}
                            height={300}
                        /*options={{ maintainAspectRatio: false, responsive: false }}*/
                        />
                    </div>
                </div>
            );
        } else {
            return (
                <div className="App-header">
                    <p>Loading...</p>
                </div>
            );
        }
    }
}


export default App2;
