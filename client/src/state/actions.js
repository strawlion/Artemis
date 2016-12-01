import ActionType from './ActionType';

export default {
    filterChanged: (category, id) => ({ type: ActionType.FILTER_CHANGED, category, id }),
    personsChanged: persons => ({ type: ActionType.PERSONS_CHANGED, persons }),
    personUpdated: person => ({ type: ActionType.PERSON_UPDATED, person }),
};