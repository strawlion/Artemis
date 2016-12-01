export default function filtersReducer(state = {}, action) {
    switch (action.type) {
        case 'FILTER_CHANGED':
            const categoryName = action.category.displayName;
            const newState = Object.assign({}, state);
            const category = newState[categoryName] = (newState[categoryName] || []).slice();

            const alreadyExists = category.includes(action.id);
            newState[categoryName] = alreadyExists  ? category.filter(id => id !== action.id)
                                                    : category.concat(action.id);
            return newState;
        default:
            return state;
    }
};