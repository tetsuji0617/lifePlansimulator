import React, { Component } from 'react'

export default class MultiForm extends Component {
    constructor(props) {
        super(props)
        this.state = {
            age: 30,
            income: 450
        }
    }

    doChange(e) {
        const userValue = e.target.value
        const key = e.target.name
        this.setState({ [key]: userValue })
    }

    doSubmit(e) {
        e.preventDefault()
        const j = JSON.stringify(this.state)

        window.alert(j)
    }

    render() {
        const doSubmit = (e) => this.doSubmit(e)
        const doChange = (e) => this.doChange(e)
        return (
            <div>
                <form onSubmit={doSubmit}>
                    <div><label>年齢：</label>
                        <input name='age'
                            type='text'
                            value={this.state.age}
                            onChange={doChange} /></div>
                    <div><label>年収(万円)：</label>
                        <input name='income'
                            type='text'
                            value={this.state.income}
                            onChange={doChange} /></div>
                    <input type='submit' value='送信' />
                </form>
            </div>
        );
    }
}