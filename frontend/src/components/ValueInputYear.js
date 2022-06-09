import React, { useState } from 'react'
import TextField from '@mui/material/TextField'

const ValueInputYear = (props) => {
    const [year, setYear] = useState('')
    const [yearError, setYearError] = useState(false)

    const hasError = (e) => {
        const year = e.target.value
        if (!year) {
            return true
        } else if (year.length < 4) {
            return true
        } else if (year < 1920 || 2022 < year) {
            return true
        } else {
            return false
        }
    }

    return (
        <>
            <TextField
                error={yearError}
                id="input-year"
                label="Birth Year"
                valiant="outlined"
                inputProps={year}
                onChange={(e) => {
                    if (!hasError(e)) {
                        props.setValue({ ...props.value, [props.title]: e.target.value })
                        setYear(e.target.value)
                        setYearError(false)
                    } else {
                        setYearError(true)
                    }
                }}
                margin="normal"
            />
        </>
    )
};

export default ValueInputYear;


