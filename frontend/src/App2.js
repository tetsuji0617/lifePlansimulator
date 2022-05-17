import React, { Component } from 'react'
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';


ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);


class App2 extends Component {

    constructor(props) {
        super(props)
        console.log('constructor')
        this.state = { loading: false }
    }

    componentWillMount() {
        console.log('componentWillMount')
        fetch('http://localhost:8080/api/getSample', { method: 'GET' })
            .then(response => response.json())
            .then(responseData => {
                this.setState({
                    loading: true,
                    data: responseData
                })
            }).catch((error) => {
                console.error(error);
            });;
    }

    render() {
        const datasetData = this.state.data
        console.log(datasetData)
        if (this.state.loading) {
            return (
                <Bar data={datasetData}
                    width={300}
                    height={300}
                    options={{ maintainAspectRatio: false, responsive: false }} />
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
