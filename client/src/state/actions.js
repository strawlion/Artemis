import ActionType from './ActionType';

export default {
    searchTextChanged: searchText => ({ type: ActionType.SEARCH_TEXT_CHANGED, searchText }),
};