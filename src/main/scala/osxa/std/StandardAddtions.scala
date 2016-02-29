//     Project: scalajs-osxa
//      Module: lib
// Description: Façade trait for the OS X Automation StandardSuite.

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa.std

import de.surfice.smacrotools.JSOptionsObject
import osxa.Path

import scala.language.experimental.macros
import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait StandardAddtions extends js.Object with UserInteraction

@js.native
trait UserInteraction extends js.Object {
  import UserInteraction._
  def beep(params: js.UndefOr[js.Object] = js.undefined): Unit = js.native
  @JSName("chooseApplication")
  def callChooseApplication(params: js.UndefOr[js.Object] = js.undefined): StandardSuite = js.native
  @JSName("chooseColor")
  def callChooseColor(params: js.UndefOr[js.Object] = js.undefined): RGBColor = js.native
  @JSName("chooseFile")
  def callChooseFile(params: js.UndefOr[js.Object] = js.undefined): Path = js.native
  @JSName("displayDialog")
  def callDisplayDialog(text: String, params: js.Object): DialogReply = js.native
  @JSName("displayNotification")
  def callDisplayNotification(text: String, params: js.Object): Unit = js.native
  @JSName("displayAlert")
  def callDisplayAlert(text: String, params: js.Object): DialogReply = js.native
}

object UserInteraction {

  @js.native
  trait DialogReply extends js.Object {
    def buttonReturned: String = js.native
    def textReturned: js.UndefOr[String] = js.native
    def gaveUp: js.UndefOr[Boolean] = js.native
  }
  type RGBColor = js.Array[Double]

  implicit class UserInteractionWrapper(val self: UserInteraction) extends AnyVal {
    def chooseApplication(withTitle: js.UndefOr[String] = js.undefined,
                          withPrompt: js.UndefOr[String] = js.undefined,
                          multipleSelectionsAllowed: js.UndefOr[Boolean] = js.undefined,
                          as: js.UndefOr[js.Any] = js.undefined
                         ): StandardSuite = self.callChooseApplication(
      ChooseOptions(withTitle = withTitle, withPrompt = withPrompt, multipleSelectionsAllowed = multipleSelectionsAllowed, as = as ))

    def chooseColor(): RGBColor = self.callChooseColor()

    def chooseFile(withPrompt: js.UndefOr[String] = js.undefined,
                   ofType: js.UndefOr[js.Array[String]] = js.undefined,
                   defaultLocation: js.UndefOr[Path] = js.undefined,
                   invisibles: js.UndefOr[Boolean] = js.undefined,
                   multipleSelectionsAllowed: js.UndefOr[Boolean] = js.undefined,
                   showingPackageContents: js.UndefOr[Boolean] = js.undefined
                  ): Path = self.callChooseFile(ChooseOptions(
      withPrompt = withPrompt, ofType = ofType, defaultLocation = defaultLocation, invisibles = invisibles,
      multipleSelectionsAllowed = multipleSelectionsAllowed, showingPackageContents = showingPackageContents
    ))

    def displayDialog(text: String,
                      defaultAnswer: js.UndefOr[String] = js.undefined,
                      hiddenAnswer: js.UndefOr[Boolean] = js.undefined,
                      buttons: js.UndefOr[js.Array[String]] = js.undefined,
                      defaultButton: js.UndefOr[js.Any] = js.undefined,
                      cancelButton: js.UndefOr[js.Any] = js.undefined,
                      withTitle: js.UndefOr[String] = js.undefined,
                      withIcon: js.UndefOr[js.Any] = js.undefined,
                      givingUpAfter: js.UndefOr[Int] = js.undefined): DialogReply = self.callDisplayDialog(text,
      DialogOptions(defaultAnswer,hiddenAnswer,buttons,defaultButton,cancelButton,withTitle,withIcon,givingUpAfter)
    )
    def displayNotification(text: String,
                            withTitle: js.UndefOr[String] = js.undefined,
                            subtitle: js.UndefOr[String] = js.undefined,
                            soundName: js.UndefOr[String] = js.undefined): Unit = self.callDisplayNotification(text,
      DialogOptions(withTitle = withTitle, subtitle = subtitle, soundName = soundName)
    )
    def displayAlert(text: String,
                     message: js.UndefOr[String] = js.undefined,
                     as: js.UndefOr[String] = js.undefined,
                     buttons: js.UndefOr[js.Array[String]] = js.undefined,
                     defaultButton: js.UndefOr[js.Any] = js.undefined,
                     cancelButton: js.UndefOr[js.Any] = js.undefined,
                     givingUpAfter: js.UndefOr[Int] = js.undefined): DialogReply = self.callDisplayAlert(text,
      DialogOptions(message=message, as=as, buttons=buttons, defaultButton=defaultButton,cancelButton=cancelButton,givingUpAfter=givingUpAfter)
    )
  }

  @JSOptionsObject
  private case class DialogOptions(defaultAnswer: js.UndefOr[String] = js.undefined,
                                   hiddenAnswer: js.UndefOr[Boolean] = js.undefined,
                                   buttons: js.UndefOr[js.Array[String]] = js.undefined,
                                   defaultButton: js.UndefOr[js.Any] = js.undefined,
                                   cancelButton: js.UndefOr[js.Any] = js.undefined,
                                   withTitle: js.UndefOr[String] = js.undefined,
                                   withIcon: js.UndefOr[js.Any] = js.undefined,
                                   givingUpAfter: js.UndefOr[Int] = js.undefined,
                                   subtitle: js.UndefOr[String] = js.undefined,
                                   soundName: js.UndefOr[String] = js.undefined,
                                   message: js.UndefOr[String] = js.undefined,
                                   as: js.UndefOr[String] = js.undefined
                                  ) extends js.Object

  @JSOptionsObject
  private case class ChooseOptions(withTitle: js.UndefOr[String] = js.undefined,
                                   withPrompt: js.UndefOr[String] = js.undefined,
                                   multipleSelectionsAllowed: js.UndefOr[Boolean] = js.undefined,
                                   as: js.UndefOr[js.Any] = js.undefined,
                                   ofType: js.UndefOr[js.Array[String]] = js.undefined,
                                   defaultLocation: js.UndefOr[Path] = js.undefined,
                                   invisibles: js.UndefOr[Boolean] = js.undefined,
                                   showingPackageContents: js.UndefOr[Boolean] = js.undefined
                                  ) extends js.Object
}
