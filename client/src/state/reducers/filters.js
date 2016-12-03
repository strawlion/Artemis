export default function filtersReducer(state = {}, action) {
    switch (action.type) {
        case 'FILTER_CHANGED':
            const newState = Object.assign({}, state);
            const currentValue = newState[action.category.id];
            const alreadyExists =currentValue === action.id;
            if (alreadyExists) {
                delete newState[action.category.id];
            }
            else {
                newState[action.category.id] = action.id;
            }
            return newState;
        default:
            return state;
    }
};