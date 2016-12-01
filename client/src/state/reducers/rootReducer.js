import { combineReducers } from 'redux';

import persons from './persons';
import filters from './filters';

export default combineReducers ({
    persons,
    filters,
})