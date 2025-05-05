[33m85d352b[m[33m ([m[1;36mHEAD[m[33m -> [m[1;32mfeature/initGame[m[33m)[m [CheckStyle] fixed some checkStyle
[33mbb9c87f[m [Test] Fixed tests for Configuration
[33m96cf12d[m [Configuration] Refactor Configuration class to delegate operation to ResourceLoader for load file data
[33mbb8cd49[m [CheckStyle] fixed BankAccountFactory & Impl
[33me6ca170[m fixed test for BankAccountFactoryImpl
[33mb76471e[m added test for BankAccountFactoryImpl
[33m86cc6ce[m Implement check for initBalance in the constructor of BankAccountFactoryImpl
[33m000e2d2[m changed 'starterBalance' in 'initBalance' and added a custom funtion during a creation of a bank account with check
[33m6428e3c[m[33m ([m[1;31morigin/feature/initGame[m[33m)[m Implement an error pop-up for closing the app if the import of JSON fail
[33md334847[m Introduce ResourceLoader for common import of files, implemented in RulesWindowView and MainMenuControllerImpl
[33m9fb2171[m fixing spotBugs and the BankAccountFactoryImpl
[33m618cf65[m added creation of players' BankAccount according the type chosen in MainMenu with pattern factory
[33m5c97c83[m added first part of controller.onClickStart for init all the game
[33md85113b[m change javadoc in BankAccountFactory to be more generic with creation (BankAccount)
[33m15c6b8a[m added interface BankAccountFactory and  relative implementation
[33m75348f5[m[33m ([m[1;31morigin/development[m[33m, [m[1;32mdevelopment[m[33m)[m Merge branch 'feature/mainMenu' into 'origin/development'
[33m1ed848c[m[33m ([m[1;31morigin/feature/mainMenu[m[33m, [m[1;32mfeature/mainMenu[m[33m)[m Merge branch 'origin/development' into feature/mainMenu, green build
[33mb698766[m fixed all spotbugs, green build
[33m8c234ac[m Moved SettingsWindowView into MainMenuView and implemented BankAccountType selection
[33m9b49a34[m fixed all spotbugs, green build
[33m45184c0[m added method in the interface Player
[33m1d3b642[m fixed build.gradle like branch development
[33m9791f7c[m fixed PlayerImplTest for supporting the ID
[33m7363213[m Implement generic interface Identifiable<> for identify player, pawn and bankAccount
[33m98f4da4[m Add support for specifying the initial amount of each bank account (starterBalance)
[33mfed5a6b[m transaction tests refactoring following changes in implementation
[33me52bc15[m refactoring following change in canBeApplied method in RentOption interface
[33mb754136[m implement RentOptionFactory
[33mb478201[m fix canBeApplied method in RentOption
[33mbd97de5[m moved static factory method baseRentOption in RentOption interface
[33mdb7a9f4[m change javadoc RentOption interface
[33mf6ab352[m refactor class ImmutableBankAccountView
[33md7a712f[m integrated class ImmutableTitleDeedCopy
[33m67a8155[m add and integrated in BankImpl ImmutableBankAccountView class
[33m4b2c7dc[m add method getTitleDeedsByGroup in BankImpl class
[33m9c2fcf4[m correct toString in WithDrawCheckBankAccount
[33mdfc12fe[m let BankAccountDecorators extend BankAccountDecorator
[33m86c1ad5[m create abstract class BankAccountDecorator
[33mad9d4a4[m renamed getPaymentFromBank to receivePaymentFromBank
[33ma634260[m implement makePaymentToBank in BankImpl
[33mbf6fa8f[m implement getPaymentToBank method
[33m52f1c91[m add amount parameters to getPaymentToBank and makePaymentToBank methods in Bank interface
[33m39a9f6a[m[33m ([m[1;31morigin/banksup[m[33m)[m adding method to bank interface
[33m7cd6c99[m implemented Test for Configuration, also added resources like: invalid_config, valid_config
[33md40367d[m[33m ([m[1;31morigin/feature/Bank[m[33m, [m[1;32mfeature/Bank[m[33m)[m add tests for getTitleDeedByOwner
[33meac4ef4[m add getTitleDeedsByOwner method in Bank interface and implementations
[33mb3f233f[m fix tests after change in BankImpl constructor
[33m5465996[m change BankImpl constructor parameters from lists to sets
[33mfe45eb7[m add thrown exceptions in Bank interface javadoc
[33m608b20d[m checkstyle corrections
[33ma802f63[m fix equals and impl hashcode for BaseTitleDeed class
[33m53b26fb[m fix equals and impl hashcode for BankAccount decorators and SimpleBankAccountImpl
[33m2026dd5[m fix payRentViolatesWithdrawConditionsOfThePayerBankAccount in BankTest class
[33m5420a9a[m Implement SettingsWindowView for selecting game mode
[33m4e061b7[m fixing spotBugs
[33m6779194[m Introduce GuiUtils for common window setup and apply to MainMenuView and RulesWindowView
[33mc2bb483[m fix tests in BankTest class
[33m325be7d[m implement method payRent in BankImpl class
[33mb413e3d[m fix bug equals method in BaseTitleDeed
[33mbf2c5ad[m fix bug in equals method in SimpleBankAccountImpl
[33mbc56206[m implement equals and toString in BaseTitleDeed class
[33mf0f1ece[m add equals and toString to BankAccount decorators
[33mc79e495[m fix test sellPropertyWithNoOwner
[33me21d946[m implement sellTitleDeed in BankImpl class
[33m6a9fced[m implement getTitleDeed and getBankAccount in BankImpl class
[33ma5c56e4[m implement buyTitleDeed in BankImpl class
[33mbfeba92[m refactor getOwner -> getPlayerName in BankAccount interface
[33mfd5f548[m edit BankAccount interface javadoc
[33me0498e1[m add check for empty lists in BankImpl constructor
[33ma318ee2[m implement constructor tests in BankTest
[33me758106[m refactor test accounts into account list
[33m6293ae8[m wrote constructor for BankImpl class
[33m53d371e[m implement new tests after interface refactoring in BankTest
[33me410d5a[m refactor and checkstyle corrections
[33m4002eaa[m add method getTitleDeed and refactoring of Bank interface
[33m575011c[m add javadoc comment for class BaseTitleDeed
[33ma878ce4[m refactor methods' signatures
[33m57f6902[m add javadoc and refactoring
[33mf16bfb0[m checkstyle and pmd corrections in BaseTitleDeedTest
[33m7c045b4[m removed unnecessary tests following BankAccount implementaion changes
[33mcddaa40[m implement testGetCorrectRentPrice
[33m0b26a60[m Add base rent parameter to constructor
[33mdead7f5[m implement getRent and getRentOptions
[33mb5c2b1b[m add guava library to project
[33m8095d87[m add canBeApplied method in RentOption interface
[33m0486a50[m add static factory method in RentOptionImpl
[33m34374e0[m create record implementation of RentOption interface
[33mc515cc0[m refactor method names back to original version in RentOption interface
[33ma2dd610[m refactor method name in RentOption interface
[33m91548a5[m refactor method name price to getPrice in RentOption interface
[33m3e14050[m change return type of getRent method
[33mc31e494[m implement testGetAllRentOptions in BaseTitleDeedTest
[33mab1e760[m add RentOption interface
[33m67ecd4d[m change constructor in BaseTitleDeedTest
[33md4942f4[m Convert RulesWindow to modal JDialog to block interaction with main window
[33mf224a1c[m fixed exceptions and messages of error in Configuration
[33m93c8025[m fixed exeptions in Configuration
[33m2bfa998[m fixed checkstyles error
[33mabdd9ce[m added check for null value in fontName (Configuration)
[33m8c56794[m Add support for specifying the font to use (font name)
[33m9570418[m Add support for specifying the filename used to load the game rules
[33m44e4234[m Initial implementation of BaseTitleDeed
[33mdef91d4[m change return type of getOwner to Optional<String>
[33ma9f0cce[m initial tests for Title Deed
[33m8ec8942[m fixed horizontalAlignment in MainMenuView
[33me44414a[m fixed horizontalAlignment in MainMenuView
[33m6eb7e3e[m added the creation of the players with pattern factory in MainMenuControllerImpl
[33m9e9654e[m added Javadoc comment in the interface MainMenuController
[33m6279710[m fixed setSize(width, width) --> setSize(width, height) in RulesWindow
[33m4b807e9[m Add support for custom font size configuration
[33mbd54bb8[m Refactor GUI to delegate counter operations to controller
[33m7f4838f[m fixed chechStyleError in PlayerImplTest
[33ma315e17[m fixed trailing spaces in Configuration
[33mc7b4f5d[m fixed chechStyleError in MainMenuView
[33m45f0191[m fixed chechStyleError in Configuration
[33m03e1f70[m fixed chechStyleError in PlayerImpl
[33m1e7b126[m fixed chechStyleError in LaunchApp
[33m5673a41[m fixed Javadoc Comment in PlayerSetup
[33mcf508d4[m added Test for PlayerImpl
[33m86af2e0[m implemented pattern factory (static version) in PlayerImpl for the creation of the players
[33m98e086b[m added an head comment in config.yml
[33m1c5ad80[m now MainMenuView support a Configuration based from Configuration.java, also implemented in LaunchApp
[33ma261395[m fixed config.yml for a design 'key: flat values', added method configureFromFile() and a parseColor() in Configuration.java
[33m9bb1ed4[m rename RulesWindow --> RulesWindowView, MainMenuGUI --> MainMenuView, MainMenuLogic --> MainMenuController, MainMenuLogicImpl --> MainMenuControllerImpl, also delete extra settings added to build.gradle
[33m4f1e2da[m in Configuration.java, changed modifiers to final where possible
[33ma07df48[m refactoring tests after change in SimpleBankAccountImpl constructor
[33mfd0185d[m create decorator classes for SimpleBankAccountImpl
[33m5aabf93[m checkstyle corrections to Bank and BankAccount interfaces
[33m943becb[m Added config.yml for external configuration, implemented Configuration.java with builder pattern, updated LaunchApp to load configuration
[33m41e501a[m refactor simple BankAccount implementation
[33md7f5085[m fixed some costants' name in MainMenuGUI
[33m29994fd[m renamed BankAccountImpl to SimpleBankAccountImpl
[33ma80d736[m create BankImpl class
[33m583a8bc[m implement getOwner in BankAccountImpl
[33m9747060[m refactored BankAccountImpl in package bankAccounts
[33mcd3e847[m add method getOwner in BankAccount interface
[33mdafaed5[m renamed isAccountValid to canContinue
[33m276b2c0[m wrote new tests for Bank following change in interface methods
[33m3d3e7d2[m add payRent and getBankAccount methods in Bank interface
[33me3ab045[m initial bankTest implementation
[33ma119e7f[m delete TransactionManagerImpl
[33m1595386[m move bankaccountTest to package transactions
[33m1ccde0f[m refactor methods in Bank interface
[33m2fe0882[m renamed TransactionManager to Bank
[33m932397c[m adding @Override in methods in MainMenuLogicImpl and PlayerImpl
[33mdd80f5b[m fixed Javadoc comments in PlayerImpl, MainMenuLogicImpl, LaunchApp
[33m0103a30[m added an empty private constructor for LaunchApp
[33mb063a25[m deleted trailing spaces in PlayerImpl
[33m02a1159[m fixed some Javadoc comments, added Costants instead of Magic Number in RuleWindow, deleted trailing spaces in MainMenuGUI
[33m61196b2[m fixed some Javadoc comments, added Costants instead of Magic Number in MainMenuGUI
[33m8d6c667[m added missing Javadoc comments
[33m8b4700b[m added Javadoc comments and declared final parameters and variables
[33m0a38a43[m implements check for duplicates resources in build.gradle
[33m715fa20[m added LaunchApp
[33m7ec59d6[m fixed import in mainMenuGUI
[33m0f9a29d[m added mainMenuGUI and rulesWindow
[33m7206e5b[m added mainMenuLogic interface and implementation
[33m17df692[m added playerSetup
[33m100c107[m updated interface Player and class PlayerImpl
[33m593afab[m deleted temp.txt from monopoly/controller/
[33m49e13ab[m deleted temp.txt from monopoly/utils/
[33m4916ea4[m renamed it/unibo/monopoly/resources --> it/unibo/monopoly/utils
[33mbd47929[m added resources folder
[33mcc91a87[m updated build.gradle with a path to the resources directory
[33mc300ae3[m[33m ([m[1;31morigin/feature/bankAccount[m[33m)[m Checkstyle corrections in BankAccountTest and BankAccountImpl
[33m6869958[m implement base version of isAccountValid and isBankrupt
[33m1aa4062[m added account validity tests and general refactoring
[33mf1b3485[m remove withdraw without sufficient balance test
[33m56331d0[m remove placeholder file
[33mffef6f6[m add methods isAccountValid and isBankrupt to BankAccount interface
[33m24ab24f[m Merge branch 'development' into feature/bankAccount
[33mcea8adb[m add GameController and MainView interface
[33m6edfaf7[m delete placeholder file
[33md3402b7[m Create LICENSE
[33m9a2bafb[m[33m ([m[1;31morigin/documentation/reportInitialStatus[m[33m)[m Merge branch 'development' into documentation/reportInitialStatus
[33m6b39983[m correct architecture design part
[33m0f43904[m correct analysis section
[33m1d04bd0[m add new entity diagram
[33mcc662be[m corrections analysis section
[33mb1f638f[m add model architecture diagram
[33mb5c876c[m added entity diagram image
[33mfa0fe20[m fix style errors in BankAccount and BankAccountImpl
[33mf0dec38[m style checks corrections in bankAccountTest
[33m4124496[m fix withdraw method
[33maaf56f7[m fix test parameters
[33m530ed11[m fix imports in BankAccountTest
[33m5ee6995[m add default constructor
[33m1df4873[m implement withdraw in BankAccountImpl
[33ma4839fa[m getBalance and deposit methods in BankAccountImpl
[33m1928d75[m wrote documentation for BankAccount interface
[33m8ac825c[m deleted report.log file
[33m35fc0ce[m initial version of design chapter
[33mb6948d4[m initial version of analysis chapter
[33m32e0402[m created analysis chapter
[33md39f8f6[m modified gitignore to exclude latex compilation files
[33m6c5de35[m moved output files for report compilation
[33m64a3514[m Front page of report
[33mdabfadb[m added provisional title in report
[33m1e1cae9[m Initial configuration for report
[33m4095031[m Merge branch 'development' into feature/bankAccount Syncing feature/bankAccount branch to latest changes on development
[33m1c92607[m renamed model packages to lowercase name
[33meed4760[m Merge branch 'development' into feature/bankAccount Syncing with development
[33m0300b81[m Merge branch 'development' of https://github.com/AlessioMinniti/OOP24-mpoly into clean-build clean build
[33mb59b390[m final fix in board
[33m9bb8ae3[m fixed unused attributes
[33me672162[m fix constructors
[33mdc876d2[m withdraw with sufficient balance test
[33ma2a1dfb[m check intial balance and deposit positive amount tests
[33m7c4f97b[m add getBalance in BankAccount interface
[33mdc212bc[m fixed position and turnation manager implementations
[33m0eaf882[m fixed dice and player implementations
[33m112ddbd[m fixed turnation
[33m49799a6[m withdraw with insufficient balance test
[33m6a3257c[m fixed transactions
[33m1e57eaa[m withdraw negative amount test
[33md773ac8[m fixed property and special implementations
[33m09c9e2d[m refactor deposit negative amount test
[33me82c439[m fixed board and effect implementation
[33m29e64f9[m impl deposit negative amount test
[33m7f8c755[m fix gameboard api
[33m2d7c5c4[m initial methods for BankAccountTest
[33m57b80ea[m fix board
[33m0a197db[m fixed checkstyle turnation impl
[33m6200083[m added turnation api's javadoc comments
[33mb9071b0[m fix checkstyle transactions final parameters
[33mddc40cf[m fix checkstyle transactions implementations
[33mf746680[m fix checkstyle transactions
[33mcd3d859[m added javadoc comments
[33meb1fad3[m created BankAccountTest class
[33m3730a6a[m fix checkstyle PropertyImpl
[33m5bdd65f[m fix checkstyle EffectImpl
[33me34869d[m fix checkstyle final BoardImpl
[33m170db77[m fix checkstyle BoardImpl
[33m5975375[m Add report folder to contain files for building report
[33m01ea64f[m fix checkstyle for Tile
[33medba76c[m fix checkstyle for Special
[33madcd70c[m fix checkstyle for Effect and Property
[33m72ac051[m fix checkstyleBoard
[33m99e6989[m placeholder readme
[33m63fb749[m add override annotations
[33m8ff863a[m set imports
[33m15d7746[m fix annotations
[33mbd2de65[m Fix pair import in TurnationManager
[33m0311492[m Add apache.commons.commons-lang3 library
[33me0aab9f[m fixed imports for classes in Transactions package
[33m2fb73e7[m renamed common folder to resources
[33m87f010b[m moved controller folder into it.unibo.monopoly package
[33m9aecf19[m moved view folder into it.unibo.monopoly package
[33m8a39db6[m moved model into it.unibo.monopoly package
[33m9d15b88[m Refactored controller package out of it.unibo
[33mfcf3836[m refatcored common package out of it.unibo
[33m132a9d2[m Refactored model package out of it.unibo
[33mcf67156[m refactored position into turnation package
[33m37e2cf1[m Refactored player and dice packages into turnation package
[33m7b1da99[m Refactored turnationManager package components into turnation package
[33me775f70[m Refactored effect package components into gameBoard package
[33mea7a9eb[m Refactored Special package components into gameBoard package
[33m1bbc88c[m Refactored property package into gameBoard
[33m97dae42[m Refactored Card into gameBoard.api package
[33m5675308[m Refactored board package into gameBoard package
[33mc5d5146[m creations of models Transactions package
[33ma340c11[m fixed imports in BankAccount
[33m1648245[m fixed imports in Board
[33mc333aa1[m fixed imports in Dice
[33m0003637[m fixed imports in Effect
[33mdc2dbef[m fixed imports in Player
[33m42aa61c[m fixed imports in Position
[33m4b1dbfc[m fixed imports in Property
[33mdf8a6f1[m fixed imports in Special
[33m8fa4b47[m fixed imports in TransactionManager
[33m24807b8[m fixed imports in TurnationManager
[33me1945fe[m Initial upload of Player
[33m9139fdd[m Initial upload of Effect
[33m671f90c[m Initial upload of Dice
[33ma57deb8[m Initial upload of Card
[33m04dca64[m Initial upload of Board
[33me82e901[m Initial upload of BankAccount
[33ma79d981[m Initial upload of TurnationManager
[33m35bb825[m Initial upload of TransactionManager
[33mf6f6324[m Initial upload of Special
[33m1c7f0ff[m Initial upload of Property
[33m666a2ad[m Initial upload of Position
[33m1a26b68[m remove organisation templates
[33md10556a[m Add .vscode folder to gitignore
[33m272ad78[m Add .DS_Store to gitignore
[33m63d1b5c[m add folder suddivision
[33m046d283[m add turnationManager and tiles' interfaces
[33m2724656[m add board and tile interfaces
[33m2a227d4[m fix folder
[33m1d73010[m[33m ([m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m, [m[1;32mmaster[m[33m)[m Initial commit
