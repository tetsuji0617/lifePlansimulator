import React, { useState, useEffect } from 'react'
import { Chart, registerables } from 'chart.js';
import { Bar } from 'react-chartjs-2';
import ValueInput from './components/ValueInput'


Chart.register(...registerables)

const App3 = () => {

    const [loading, setLoding] = useState(false)
    const [age, setAge] = useState(35)
    const [income, setIncome] = useState(500)
    const [expense, setExpense] = useState(20)
    const [jsonData, setJsonData] = useState({});

    const inputChange = (e) => {
        const inputValue = e.target.value
        const inputKey = e.target.name
        this.setState({
            [inputKey]: inputValue
        })
    }

    const ageChanged = (e) => {
        const ageValue = e.value
        setAge(ageValue)
    }
    const incomeChanged = (e) => {
        const incomeValue = e.value
        setIncome(incomeValue)
    }

    useEffect(() => {
        console.log('useEffect')
        console.log(income)
        console.log(age)
        console.log(expense)
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ income: income, age: age, expense: expense })
        }
        fetch('http://localhost:8080/api/getLifePlan2', requestOptions)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log(responseJson)
                setJsonData(responseJson)
                setLoding(true)
            })
        console.log('useEffect End')
    }, [age, income, expense])

    if (loading) {
        console.log('render')

        return (
            <div>
                <div>
                    <ValueInput title='age'
                        onChange={ageChanged}
                        value={age} />
                    <ValueInput title='income'
                        onChange={incomeChanged}
                        value={income} />
                    <ValueInput title='expense'
                        onChange={inputChange}
                        value={expense} />
                    <Bar data={jsonData}
                        width={500}
                        height={500}
                    options={{ maintainAspectRatio: false, responsive: false }}
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


export default App3;
