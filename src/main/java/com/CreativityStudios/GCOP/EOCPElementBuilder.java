/**
 * EOCPElementBuilder class
 *
 * This class constructs EOCPElements and their associated values based
 * off the Expirī Object Communication Protocol Version 1 (EOCPv1)
 *
 * Creating EOCPElements should follow the criteria outlined below:
 *      1) Use the create() method to create a new EOCPElement
 *      2) Use the setValueType() method to set the HTML elements type
 *      3) Use the setValue methods to set all other fields you need for your HTML element
 *      4) If you are adding children to this HTML element
 *          - Repeat steps 1-3
 *          - Use setChild() to set the newest EOCPElement created by the create() method to the child
 *          of the last created EOCPElement
 *          - To add more than one child to the HTML element, repeat steps 1-4
 *      5) Use the build() method to create the EOCPElement for use
 *
 * Breaking from this procedure will create unwanted parental connections or cause an Exception to be thrown
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere. All Rights Reserved.
 */

package com.CreativityStudios.GCOP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EOCPElementBuilder {
    LinkedList<EOCPElement> eocpLinkedList = new LinkedList<>();
    LinkedList<EOCPValues> eocpValuesLinkedList = new LinkedList<>();
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * Creates a new EOCPElement
     *
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder create() {
        eocpLinkedList.add(new EOCPElement());
        eocpValuesLinkedList.add(new EOCPValues());

        return this;
    }

    /**
     * Sets the current EOCPElement's "Type" value
     *
     * @param type String HTML element's type
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder setValueType(String type) {
        eocpValuesLinkedList.getLast().setHtmlType(type);

        return this;
    }

    /**
     * Sets the current EOCPElement's "Content" value
     *
     * @param content String HTML element's content
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder setValueContent(String content) {
        eocpValuesLinkedList.getLast().setHtmlContent(content);

        return this;
    }

    /**
     * Sets the current EOCPElement's "Id" value
     *
     * @param id String HTML element's id
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder setValueId(String id) {
        eocpValuesLinkedList.getLast().setHtmlId(id);

        return this;
    }

    /**
     * Sets the current EOCPElement's "Class" value
     *
     * @param htmlClass String HTML element's Class
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder setValueClass(String htmlClass) {
        eocpValuesLinkedList.getLast().setHtmlClass(htmlClass);

        return this;
    }

    /**
     * Sets the current EOCPElement's "Style" value
     *
     * @param style String HTML element's CSS styles
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder setValueStyle(String style) {
        eocpValuesLinkedList.getLast().setHtmlStyle(style);

        return this;
    }

    /**
     * Sets the current EOCPElement as a child of the last created EOCPElement
     *
     * @return EOCPElementBuilder the current builder
     */
    public EOCPElementBuilder setChild() {
        EOCPValues parentValues = eocpValuesLinkedList.get(eocpValuesLinkedList.size() - 2);
        EOCPElement child = eocpLinkedList.getLast();
        EOCPValues childValues = eocpValuesLinkedList.getLast();


        child.setValues(childValues);
        ArrayList<EOCPElement> parentChildrenValues = new ArrayList<>();

        if(parentValues.getHtmlChildren() != null) {
            for (int i = 0; i < parentValues.getHtmlChildren().size(); i++) {
                parentChildrenValues.add(parentValues.getHtmlChildren().get(i));
            }
        }
        parentChildrenValues.add(child);

        parentValues.setHtmlChildren(parentChildrenValues);

        eocpLinkedList.remove(child);
        eocpValuesLinkedList.remove(eocpValuesLinkedList.getLast());

        return this;
    }

    /**
     * Builds the entire EOCPElement
     *
     * @return EOCPElement element that the builder created
     */
    public EOCPElement build() {
        eocpLinkedList.getLast().setValues(eocpValuesLinkedList.getLast());

        return eocpLinkedList.getLast();
    }

}

