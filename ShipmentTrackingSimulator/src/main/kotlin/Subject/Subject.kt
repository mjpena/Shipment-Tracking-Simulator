package subject

import observer.TrackerViewHelper

interface Subject {
    fun notifyObserver()
    fun registerObserver(trackerViewHelper: TrackerViewHelper)
    fun removeObserver(trackerViewHelper: TrackerViewHelper)
}