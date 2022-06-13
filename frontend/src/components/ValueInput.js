import React, { useState } from 'react'
import Grid from '@mui/material/Grid'
import TextField from '@mui/material/TextField'


const ValueInput = (props) => {
    const [input, setInput] = useState(props.value)
    const [inputError, setInputError] = useState('')


    const handleBlur = (e) => {
        console.log('valueinput.handleBlur')
        const v = e.target.value
        const newValue = v.replace(/[^0-9.]+/g, '')
        if (!newValue) {
            setInputError('required')
        } else {
            setInputError()
        }
    }

    const hasError = (e) => {
        const value = e.target.value
        const newValue = value.replace(/[^0-9.]+/g, '')
        if (!newValue) {
            return true
        } else {
            return false
        }
    }

    const gridContainerStyle = {
        verticalAlign: "center",
        border: "1px solid grey ",
        padding: "10px",
        margin: "10px",

    }

    return (
        <Grid container spaceing={1} style={gridContainerStyle}>
            <Grid item xs={3}>
                <label>{props.title}</label>
            </Grid>
            <Grid item xs={1}>
                <label>:</label>
            </Grid>
            <Grid item xs={8}>
                <TextField
                    error={inputError}
                    id={props.title}
                    value={input}
                    label={props.title}
                    variant="outlined"
                    inputProps={input}
                    onChange={(e) => {
                        if (!hasError(e)) {
                            props.setValue(e.target.value)
                            setInput(e.target.value)
                            setInputError(false)
                        } else {
                            setInputError(true)
                        }
                    }}
                    />
            </Grid>
        </Grid>
    )
};

export default ValueInput;