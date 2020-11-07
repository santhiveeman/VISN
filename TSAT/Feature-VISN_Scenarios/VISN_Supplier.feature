Feature: Automation Testing for VISN Application

  @Supplier
  Scenario: Supplier flow

    When open 'chrome'
    And navigate into 'https://devvisn.web.app/'
    And wait for '5000' seconds
    And type 'garagecv11de@yahoo.com' into 'emailAddress'
    And type '12345678' into 'password'
    And click 'signin'
    And wait for '15000' seconds
    #When select new Order
    And click 'view'
    And wait for '1000' seconds
    And type 'November 27th 05:25' into 'datepicker'
    And click 'radio1'
    And click 'radio2'
    And wait for '1000' seconds
    And click 'addAnotherDate'
    And type 'November 29th 05:25' into 'datePicker1'
    And wait for '1000' seconds
    And click 'radio3'
    And click 'radio4'
    And wait for '1000' seconds
    And click 'jobSheet'
    And wait for '1000' seconds
    And type 'Tyre' into 'desc'
    And wait for '1000' seconds
    And click 'ReasonCode'
    And wait for '1000' seconds
    And  click 'ReasonCodeValue'
    #And type 'Damage' into 'ReasonCode'
    And type 'Tyrechange' into 'partsDesc'
    And type '150' into 'partsPrice'
   # And type '10' into 'partsDiscount'

   # And type '5' into 'partManufacturerGoodwill'
    And type '200' into 'labourRate'
    And clear 'LabourTime'
    And type '1' into 'LabourTime'
    #And type '1' into 'labourManufacturerGoodwill'
    And click 'VATRate'
    And click 'VATRateValue'
    And type '5' into 'adjustedPrice'
    And click 'additionalInfo'

    And type 'we need to change repaired tyre for this jobsheet' into 'notes'
    And  click 'timeRequiredToComplete'
    And  click 'timeRequiredToComplete'
   # And type '1 day' into 'timeRequiredToCompleteValue'
    And wait for '500' seconds
    And click 'summary'
    And click 'send'






