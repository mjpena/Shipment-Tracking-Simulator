package subject

import observer.TrackerViewHelper

interface Subject {
    fun notifyObservers()
    fun registerObserver(trackerViewHelper: TrackerViewHelper)
    fun removeObserver(trackerViewHelper: TrackerViewHelper)
}