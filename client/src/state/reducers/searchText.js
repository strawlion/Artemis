export default function searchTextReducer(state = null, action) {
    switch (action.type) {
        case 'SEARCH_TEXT_CHANGED':
            return action.searchText;
        default:
            return state;
    }
};