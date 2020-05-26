Feature: Uploading an excel file to convert into pdf and downloading the same file after conversion

  Scenario Outline: Uploading and downloading a file
    Given I am on the pdf converter page <url>
    When I click on upload button to upload file
    Then file conversion is in progress
    When I select download button to download converted file
    Then file is downloaded
    Examples:
      | url   |
      | ROW1 |
     # | ROW2 |
     # | ROW3 |