import { bindable, autoinject } from "aurelia-framework";

@autoinject()
export class CollapsibleRow {
    @bindable header: String;

    constructor(element: Element) {
        // element.addEventListener('click', () => {
        //     const collapsibleHeader: any = element.querySelector('.collapsible-header');
        //     const collapsibleHeaderElement: any = $(collapsibleHeader);
        //     collapsibleHeaderElement.trigger('click');
        // });
    }

}
